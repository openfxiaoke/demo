package com.facishare.open.demo.beans.results;

import com.google.common.base.MoreObjects;

public class BindAccountResult extends BaseResult {

    private static final long serialVersionUID = -3149116924450935088L;

    /**
     * 开放平台派发的用户账号
     */
    private String openUserId;

    /**
     * 开放平台派发的公司账号
     */
    private String corpId;

    public String getOpenUserId() {
        return openUserId;
    }

    public void setOpenUserId(String openUserId) {
        this.openUserId = openUserId;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("errorMessage", errorMessage)
                .add("openUserId", openUserId)
                .add("corpId", corpId)
                .toString();
    }

}
