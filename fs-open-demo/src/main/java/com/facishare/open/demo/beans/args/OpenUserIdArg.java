package com.facishare.open.demo.beans.args;

import com.google.common.base.MoreObjects;

/**
 * 封装通过code获取 openUserId 请求参数的JaveBean
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public class OpenUserIdArg implements Arg {

    private static final long serialVersionUID = 7019560397749052017L;

    /**
     * appAccessToken
     */
    private String appAccessToken;

    /**
     * 临时身份票据
     */
    private String code;

    public String getAppAccessToken() {
        return appAccessToken;
    }

    public void setAppAccessToken(String appAccessToken) {
        this.appAccessToken = appAccessToken;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("appAccessToken", appAccessToken)
                .add("code", code)
                .toString();
    }

}
