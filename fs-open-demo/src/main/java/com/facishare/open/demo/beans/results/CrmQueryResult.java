package com.facishare.open.demo.beans.results;

import com.google.common.base.MoreObjects;

import java.util.List;
import java.util.Map;

/**
 * Crm查询结果
 * Created by zhongcy on 2017/1/6.
 */
public class CrmQueryResult extends BaseResult {

    private static final long serialVersionUID = -2706748557128822040L;

    private int totalNumber;

    private List<Map<String, Object>> datas;

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<Map<String, Object>> getDatas() {
        return datas;
    }

    public void setDatas(List<Map<String, Object>> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("totalNumber", totalNumber)
                .add("datas", datas)
                .toString();
    }
}
