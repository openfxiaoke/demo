package com.facishare.open.demo.beans.results;

import com.google.common.base.MoreObjects;

/**
 * 封装获取到的CorpAccessToken结果的JavaBean
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public class CorpAccessTokenResult extends BaseResult {

    private static final long serialVersionUID = -4995204525912210225L;

    /**
     * corpAccessToken
     */
    private String corpAccessToken;

    /**
     * corpId
     */
    private String corpId;

    /**
     * corpAccessToken 超时时间(单位为：秒)
     */
    private long expiresIn;

    public String getCorpAccessToken() {
        return corpAccessToken;
    }

    public void setCorpAccessToken(String corpAccessToken) {
        this.corpAccessToken = corpAccessToken;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
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
                .add("corpAccessToken", corpAccessToken)
                .add("corpId", corpId)
                .add("expiresIn", expiresIn)
                .toString();
    }

}
