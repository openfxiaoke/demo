package com.facishare.open.demo.beans.args;

import com.google.common.base.MoreObjects;

/**
 * 封装获取CorpAccessToken 请求参数的JaveBean
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public class CorpAccessTokenArg implements Arg {

    private static final long serialVersionUID = 119087883828028381L;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 应用秘钥
     */
    private String appSecret;

    /**
     * 永久授权码
     */
    private String permanentCode;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getPermanentCode() {
        return permanentCode;
    }

    public void setPermanentCode(String permanentCode) {
        this.permanentCode = permanentCode;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("appId", appId)
                .add("permanentCode", permanentCode)
                .add("permanentCode", permanentCode)
                .toString();
    }

}
