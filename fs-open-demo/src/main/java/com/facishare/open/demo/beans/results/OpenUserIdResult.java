package com.facishare.open.demo.beans.results;

import com.google.common.base.MoreObjects;

/**
 * 封装获取到的OpenUserId 结果的JavaBean
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public class OpenUserIdResult extends BaseResult {

    private static final long serialVersionUID = 7403143650678784355L;

    /**
     * openUserId
     */
    private String openUserId;

    /**
     * corpId
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
