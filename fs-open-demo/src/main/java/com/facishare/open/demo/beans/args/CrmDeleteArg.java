package com.facishare.open.demo.beans.args;

import com.google.common.base.MoreObjects;

/**
 * 删除CRM数据参数
 * Created by zhongcy on 2017/1/4.
 */
public class CrmDeleteArg extends BaseArg {
    private static final long serialVersionUID = -8483693972030281760L;

    private String currentOpenUserId;

    private String apiName;

    private String dataId;

    public CrmDeleteArg(String currentOpenUserId, String apiName, String dataId) {
        this.currentOpenUserId = currentOpenUserId;
        this.apiName = apiName;
        this.dataId = dataId;
    }

    public CrmDeleteArg() {
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("currentOpenUserId", currentOpenUserId)
                .add("apiName", apiName)
                .add("dataId", dataId)
                .toString();
    }
}
