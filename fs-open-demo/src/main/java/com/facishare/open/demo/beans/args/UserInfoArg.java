package com.facishare.open.demo.beans.args;

import com.google.common.base.MoreObjects;

/**
 * 封装获取成员信息 请求参数的JaveBean
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public class UserInfoArg extends BaseArg {

    private static final long serialVersionUID = 8596610310375967604L;

    /**
     * openUserId
     */
    private String openUserId;

    public String getOpenUserId() {
        return openUserId;
    }

    public void setOpenUserId(String openUserId) {
        this.openUserId = openUserId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("corpAccessToken", corpAccessToken)
                .add("corpId", corpId)
                .add("openUserId", openUserId)
                .toString();
    }

}
