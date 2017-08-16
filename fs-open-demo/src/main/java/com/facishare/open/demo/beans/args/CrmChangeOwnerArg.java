package com.facishare.open.demo.beans.args;

import com.google.common.base.MoreObjects;

/**
 * 变更负责人 参数
 * Created by huanghp on 2017/4/11
 */
public class CrmChangeOwnerArg extends BaseArg {

    private static final long serialVersionUID = 8140163474118111074L;

    private String currentOpenUserId;

    private String apiName;

    private String dataId;
    
    private String ownerOpenUserId;

    public CrmChangeOwnerArg(String currentOpenUserId, String apiName, String dataId, String ownerOpenUserId) {
        this.currentOpenUserId = currentOpenUserId;
        this.apiName = apiName;
        this.dataId = dataId;
        this.ownerOpenUserId = ownerOpenUserId;
    }

    public CrmChangeOwnerArg() {
    }

    public String getCurrentOpenUserId() {
        return currentOpenUserId;
    }

    public void setCurrentOpenUserId(String currentOpenUserId) {
        this.currentOpenUserId = currentOpenUserId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }
    
    public String getOwnerOpenUserId() {
        return ownerOpenUserId;
    }

    public void setOwnerOpenUserId(String ownerOpenUserId) {
        this.ownerOpenUserId = ownerOpenUserId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("currentOpenUserId", currentOpenUserId)
                .add("apiName", apiName)
                .add("dataId", dataId)
                .add("ownerOpenUserId", ownerOpenUserId)
                .toString();
    }
}
