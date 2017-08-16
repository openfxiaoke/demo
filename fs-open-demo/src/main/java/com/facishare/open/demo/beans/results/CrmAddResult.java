package com.facishare.open.demo.beans.results;

import com.google.common.base.MoreObjects;

/**
 * CRM crete接口返回结果
 * Created by zhongcy on 2016/12/30.
 */
public class CrmAddResult extends BaseResult {

    private static final long serialVersionUID = -6938203198421600660L;

    private String dataId;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("dataId", dataId)
                .toString();
    }
}
