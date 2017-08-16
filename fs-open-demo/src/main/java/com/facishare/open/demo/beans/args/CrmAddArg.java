package com.facishare.open.demo.beans.args;

import com.google.common.base.MoreObjects;

/**
 * CRM添加接口参数
 * Created by zhongcy on 2016/12/30.
 */
public class CrmAddArg<T> extends BaseArg{

    private static final long serialVersionUID = 9135811674969239762L;

    private String currentOpenUserId;

    private String apiName;

    private T data;

    public CrmAddArg(String currentOpenUserId, String apiName, T data) {
        this.currentOpenUserId = currentOpenUserId;
        this.apiName = apiName;
        this.data = data;
    }

    public CrmAddArg() {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("currentOpenUserId", currentOpenUserId)
                .add("apiName", apiName)
                .add("data", data)
                .toString();
    }
}
