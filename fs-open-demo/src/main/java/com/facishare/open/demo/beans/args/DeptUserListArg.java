package com.facishare.open.demo.beans.args;

import com.google.common.base.MoreObjects;

/**
 * 封装获取部门下人员列表 请求参数的JaveBean
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public class DeptUserListArg extends BaseArg {

    private static final long serialVersionUID = -6444321202791679719L;

    /**
     * 部门ID
     */
    private Integer departmentId;

    /**
     * 是否获取子部门
     */
    private boolean fetchChild;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public boolean isFetchChild() {
        return fetchChild;
    }

    public void setFetchChild(boolean fetchChild) {
        this.fetchChild = fetchChild;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("corpAccessToken", corpAccessToken)
                .add("corpId", corpId)
                .add("departmentId", departmentId)
                .add("fetchChild", fetchChild)
                .toString();
    }
}
