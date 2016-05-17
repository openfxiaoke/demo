package com.facishare.open.demo.beans.results;

import com.google.common.base.MoreObjects;


/**
 * 封装获取到的AppToken结果的JavaBean
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public class AppTokenResult extends BaseResult {

    private static final long serialVersionUID = 7698338379605078704L;

    /**
     * appAccessToken
     */
    private String appAccessToken;

    /**
     * appToken 超时时间(单位为：分钟)
     */
    private long expiresIn;

    public String getAppAccessToken() {
        return appAccessToken;
    }

    public void setAppAccessToken(String appAccessToken) {
        this.appAccessToken = appAccessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("errorMessage", errorMessage)
                .add("appAccessToken", appAccessToken)
                .add("expiresIn", expiresIn)
                .toString();
    }

}
