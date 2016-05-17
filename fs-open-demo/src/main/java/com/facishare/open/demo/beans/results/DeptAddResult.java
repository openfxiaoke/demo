package com.facishare.open.demo.beans.results;

import com.google.common.base.MoreObjects;

/**
 * Created by zhongcy on 2016/4/8.
 */
public class DeptAddResult extends BaseResult {
    
    private static final long serialVersionUID = -1877511411491870320L;
    
    /**
     * 增加的部门Id
     */
    private Integer departmentId;

    /**
     * 增加的部门排序号
     */
    private Integer order;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("errorMessage", errorMessage)
                .add("departmentId", departmentId)
                .add("order", order)
                .toString();
    }

}
