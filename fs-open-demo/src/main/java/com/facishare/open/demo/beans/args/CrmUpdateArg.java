package com.facishare.open.demo.beans.args;

import com.google.common.base.MoreObjects;

/**
 * Crm更新对象数据参数
 * Created by zhongcy on 2017/1/4.
 */
public class CrmUpdateArg<T> extends BaseArg {
    private static final long serialVersionUID = -3661876864620608225L;

    private String currentOpenUserId;

    private String apiName;

    private String dataId;

    private T data;

    public CrmUpdateArg() {
    }

    public CrmUpdateArg(String currentOpenUserId, String apiName, String dataId, T data) {
        this.currentOpenUserId = currentOpenUserId;
        this.apiName = apiName;
        this.dataId = dataId;
        this.data = data;
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
                .add("dataId", dataId)
                .add("data", data)
                .toString();
    }
}
