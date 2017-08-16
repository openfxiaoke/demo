package com.facishare.open.demo.beans.args;

import com.google.common.base.MoreObjects;

/**
 * CRM 获取字段描述
 * Created by huanghp on 2017/04/10.
 */
public class CrmDescArg extends BaseArg{

    private static final long serialVersionUID = 9135811674969239762L;

    private String currentOpenUserId;

    private String apiName;

    public CrmDescArg(String currentOpenUserId, String apiName) {
        this.currentOpenUserId = currentOpenUserId;
        this.apiName = apiName;
    }

    public CrmDescArg() {
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("currentOpenUserId", currentOpenUserId)
                .add("apiName", apiName)
                .toString();
    }
}
