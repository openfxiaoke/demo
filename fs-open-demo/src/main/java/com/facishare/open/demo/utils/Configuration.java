package com.facishare.open.demo.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 读取Properties配置信息的类
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
@Service("configuration")
public class Configuration {

    /**
     * 应用Id
     */
    @Value("${fs.appId}")
    private String appId;

    /**
     * 应用秘钥
     */
    @Value("${fs.appSecret}")
    private String appSecret;

    /**
     * 永久授权码
     */
    @Value("${fs.permanentCode}")
    private String permanentCode;

    /**
     * App 约定加密Key
     */
    @Value("${fs.token}")
    private String token;

    /**
     * App 约定加密AesKey
     */
    @Value("${fs.encodingAesKey}")
    private String encodingAesKey;

    /**
     * 绑定请求uri
     */
    @Value("${fs.authorize.url}")
    private String fsAuthorizeUrl;

    public String getAppId() {
        return appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public String getPermanentCode() {
        return permanentCode;
    }

    public void setPermanentCode(String permanentCode) {
        this.permanentCode = permanentCode;
    }

    public String getToken() {
        return token;
    }

    public String getEncodingAesKey() {
        return encodingAesKey;
    }

    public String getFsAuthorizeUrl() {
        return fsAuthorizeUrl;
    }

}
