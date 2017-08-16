package com.facishare.open.demo.beans.args;

import com.google.common.base.MoreObjects;

/**
 * Crm获取详情接口参数
 * Created by zhongcy on 2017/1/9.
 */
public class CrmGetArg extends BaseArg{

    private static final long serialVersionUID = -6899653224372546750L;

    private String currentOpenUserId;

    private String apiName;

    private String dataId;

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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("currentOpenUserId", currentOpenUserId)
                .add("apiName", apiName)
                .add("dataId", dataId)
                .toString();
    }
}
