package com.facishare.open.demo.manager.impl;

import java.util.Map;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.facishare.open.demo.beans.CorpAccessToken;
import com.facishare.open.demo.beans.args.AppTokenArg;
import com.facishare.open.demo.beans.args.CorpAccessTokenArg;
import com.facishare.open.demo.beans.results.AppTokenResult;
import com.facishare.open.demo.beans.results.CorpAccessTokenResult;
import com.facishare.open.demo.exception.AccessTokenException;
import com.facishare.open.demo.exception.AppAccessTokenRequestException;
import com.facishare.open.demo.exception.CorpAccessTokenRequestException;
import com.facishare.open.demo.manager.AccessTokenManager;
import com.facishare.open.demo.utils.Configuration;
import com.facishare.open.demo.utils.Constants;
import com.facishare.open.demo.utils.OpenAPIUtils;
import com.google.common.collect.Maps;

@Service("accessTokenManager")
public class AccessTokenManagerImpl implements AccessTokenManager {

    private static final Logger LOG = LoggerFactory.getLogger(AccessTokenManagerImpl.class);

    private static final String KEY_EXPIRES_IN = "expiresIn";

    private static final String KEY_TOKEN = "token";

    private static final String APP_ACCESS_TOKEN_KEY_PREX = "appAccessToken_";

    private static final String CORP_ACCESS_TOKEN_KEY_PREX = "corpAccessToken_";

    @Resource(name = "configuration")
    private Configuration configuration;

    /**
     * AppAccessToken、CorpAccessToken缓存
     */
    private static Map<String, Map<String, Object>> accessTokenMap = Maps.newConcurrentMap();

    private static Map<String, Object> setCorpAccessToken(CorpAccessTokenResult result) {
        CorpAccessToken corpAccessToken = new CorpAccessToken();
        corpAccessToken.setCorpAccessToken(result.getCorpAccessToken());
        corpAccessToken.setCorpId(result.getCorpId());

        Map<String, Object> token = Maps.newHashMap();
        // 减去1分钟，以免过时
        token.put(KEY_EXPIRES_IN, (result.getExpiresIn() - 15 * 60) * 1000 + System.currentTimeMillis());
        token.put(KEY_TOKEN, corpAccessToken);
        return token;
    }

    private CorpAccessTokenResult getCorpAccessToken(String appAccessToken) throws CorpAccessTokenRequestException {
        CorpAccessTokenArg arg = new CorpAccessTokenArg();
        arg.setAppAccessToken(appAccessToken);
        arg.setPermanentCode(configuration.getPermanentCode());
        return OpenAPIUtils.getCorpToken(arg);
    }

    @Override
    public void resetAppAccessToken() throws AppAccessTokenRequestException {
        accessTokenMap.remove(APP_ACCESS_TOKEN_KEY_PREX.concat(configuration.getAppId()));
        getAppAccessToken();
    }

    @Override
    public void resetCorpAccessToken() throws AccessTokenException {
        accessTokenMap.remove(CORP_ACCESS_TOKEN_KEY_PREX.concat(configuration.getAppId()).concat(
                configuration.getPermanentCode()));
        getCorpAccessToken();
    }

    @Override
    public String getAppAccessToken() throws AppAccessTokenRequestException {
        String key = APP_ACCESS_TOKEN_KEY_PREX.concat(configuration.getAppId());
        Map<String, Object> token = accessTokenMap.get(key);

        if (token != null) {
            long expiresIn = (Long) token.get(KEY_EXPIRES_IN);

            if (System.currentTimeMillis() < expiresIn) {
                return (String) token.get(KEY_TOKEN);
            }
            accessTokenMap.remove(key);
        }

        String appAccessToken = null;
        synchronized (this) {
            token = accessTokenMap.get(key);

            // 多线程环境下，其他线程可能已经获得最新appAccessToken，直接返回
            if (token != null) {
                return (String) token.get(KEY_TOKEN);
            }
            AppTokenArg arg = new AppTokenArg();
            arg.setAppId(configuration.getAppId());
            arg.setAppSecret(configuration.getAppSecret());
            AppTokenResult result = OpenAPIUtils.getAppToken(arg);
            if (result.getErrorCode() != 0) {
                throw new AppAccessTokenRequestException(result.getErrorCode(), result.getErrorMessage());
            }
            appAccessToken = result.getAppAccessToken();
            token = Maps.newHashMap();
            // 减去10分钟，以免过时
            token.put(KEY_EXPIRES_IN, (result.getExpiresIn() - 15 * 60) * 1000 + System.currentTimeMillis());
            token.put(KEY_TOKEN, appAccessToken);
            accessTokenMap.put(key, token);
            return appAccessToken;
        }
    }

    @Override
    public CorpAccessToken getCorpAccessToken() throws AccessTokenException {
        String key =
                CORP_ACCESS_TOKEN_KEY_PREX.concat(configuration.getAppId()).concat(configuration.getPermanentCode());
        Map<String, Object> token = accessTokenMap.get(key);

        if (token != null) {
            long expiresIn = (Long) token.get(KEY_EXPIRES_IN);
            if (System.currentTimeMillis() < expiresIn) {
                return (CorpAccessToken) token.get(KEY_TOKEN);
            }
            accessTokenMap.remove(key);
        }

        synchronized (this) {
            token = accessTokenMap.get(key);
            // 多线程环境下，其他线程可能已经获得最新corpAccessToken，直接返回
            if (token != null) {
                return (CorpAccessToken) token.get(KEY_TOKEN);
            }

            String appAccessToken;
            try {
                appAccessToken = getAppAccessToken();
            } catch (AppAccessTokenRequestException e) {
                LOG.error("getCorpAccessToken error message:{}, details:", e.getMessage(), e);
                // 获取appAccessToken失败就重试一次，再次失败抛出异常
                appAccessToken = getAppAccessToken();
            }

            CorpAccessTokenResult corpAccessTokenResult = null;
            try {
                corpAccessTokenResult = getCorpAccessToken(appAccessToken);
                if (corpAccessTokenResult != null && corpAccessTokenResult.getErrorCode() == 0) {
                    token = setCorpAccessToken(corpAccessTokenResult);
                } else if (corpAccessTokenResult == null
                        || corpAccessTokenResult.getErrorCode() == Constants.interfaceResponseCode.APP_ACCESS_TOKEN_EXPIRED.code) {
                    // accessToken不存在或者已经过期
                    resetAppAccessToken();
                    corpAccessTokenResult = getCorpAccessToken(appAccessToken);
                    token = setCorpAccessToken(corpAccessTokenResult);
                }

                if (token != null) {
                    accessTokenMap.put(key, token);
                }
            } catch (Exception e) {
                LOG.error("getCorpAccessToken error message:{}, details:", e.getMessage(), e);
                // 重试一次，
                corpAccessTokenResult = getCorpAccessToken(appAccessToken);
                if (corpAccessTokenResult != null && corpAccessTokenResult.getErrorCode() == 0) {
                    token = setCorpAccessToken(corpAccessTokenResult);
                    accessTokenMap.put(key, token);
                }
            }
            if (token == null) {
                throw new CorpAccessTokenRequestException(corpAccessTokenResult.getErrorCode(),
                        corpAccessTokenResult.getErrorMessage());
            }

            CorpAccessToken corpAccessToken = (CorpAccessToken) token.get(KEY_TOKEN);
            if (corpAccessToken == null) {
                throw new CorpAccessTokenRequestException(corpAccessTokenResult.getErrorCode(),
                        corpAccessTokenResult.getErrorMessage());
            }
            return corpAccessToken;
        }
    }

}
