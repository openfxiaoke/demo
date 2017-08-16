package com.facishare.open.demo.beans.results;

import com.google.common.base.MoreObjects;

import java.util.Map;

/**
 * 获取详情接口返回值
 * Created by zhongcy on 2017/1/9.
 */
public class CrmGetResult extends BaseResult{

    private static final long serialVersionUID = 4289877461866887841L;

    /**
     * 数据详情结果
     */
    private Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("data", data)
                .toString();
    }
}
