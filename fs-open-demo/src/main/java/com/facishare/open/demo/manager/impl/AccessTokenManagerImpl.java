package com.facishare.open.demo.manager.impl;

import java.util.Map;
import javax.annotation.Resource;

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
import com.facishare.open.demo.utils.OpenAPIUtils;
import com.google.common.collect.Maps;

@Service("accessTokenManager")
public class AccessTokenManagerImpl implements AccessTokenManager {

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
        // 减去3分钟，以免过时
        token.put(KEY_EXPIRES_IN, (result.getExpiresIn() - 3 * 60) * 1000 + System.currentTimeMillis());
        token.put(KEY_TOKEN, corpAccessToken);
        return token;
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
            // 减去3分钟，以免过时
            token.put(KEY_EXPIRES_IN, (result.getExpiresIn() - 3 * 60) * 1000 + System.currentTimeMillis());
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
            
            CorpAccessTokenArg arg = new CorpAccessTokenArg();
            arg.setAppId(configuration.getAppId());
            arg.setAppSecret(configuration.getAppSecret());
            arg.setPermanentCode(configuration.getPermanentCode());
            CorpAccessTokenResult corpAccessTokenResult =  OpenAPIUtils.getCorpToken(arg);
            if (corpAccessTokenResult != null && corpAccessTokenResult.getErrorCode() == 0) {
                token = setCorpAccessToken(corpAccessTokenResult);
            }

            if (token == null) {
                throw new CorpAccessTokenRequestException(corpAccessTokenResult.getErrorCode(),corpAccessTokenResult.getErrorMessage());
            }
            accessTokenMap.put(key, token);
            CorpAccessToken corpAccessToken = (CorpAccessToken) token.get(KEY_TOKEN);
            if (corpAccessToken == null) {
                throw new CorpAccessTokenRequestException(corpAccessTokenResult.getErrorCode(),corpAccessTokenResult.getErrorMessage());
            }
            return corpAccessToken;
        }
    }
}
