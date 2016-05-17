package com.facishare.open.demo.beans.args;

import com.google.common.base.MoreObjects;

/**
 * 封装账号绑定请求参数的javaBean
 * 
 * @author huanghp
 * @date 2015-09-17
 */
public class BindAccountArg implements Arg {

    private static final long serialVersionUID = 2827279520736645297L;

    /**
     * 企业应用获取到的凭证
     */
    private String appAccessToken;

    /**
     * 用户开平账号
     */
    private String openUserId;

    /**
     * 企业应用系统里的员工账号
     */
    private String appAccount;

    public String getAppAccessToken() {
        return appAccessToken;
    }

    public void setAppAccessToken(String appAccessToken) {
        this.appAccessToken = appAccessToken;
    }

    public String getOpenUserId() {
        return openUserId;
    }

    public void setOpenUserId(String openUserId) {
        this.openUserId = openUserId;
    }

    public String getAppAccount() {
        return appAccount;
    }

    public void setAppAccount(String appAccount) {
        this.appAccount = appAccount;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("appAccessToken", appAccessToken)
                .add("openUserId", openUserId)
                .add("appAccount", appAccount)
                .toString();
    }

}
