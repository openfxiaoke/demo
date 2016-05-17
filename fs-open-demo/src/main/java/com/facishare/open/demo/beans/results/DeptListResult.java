package com.facishare.open.demo.beans.results;

import java.util.List;

import com.google.common.base.MoreObjects;

/**
 * 封装获取到的部门列表结果 的JaveBean
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public class DeptListResult extends BaseResult {

    private static final long serialVersionUID = -4407072730712353168L;

    /**
     * 部门列表 @see DepartmentResult
     */
    private List<Department> departments;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("errorMessage", errorMessage)
                .add("departments", departments)
                .toString();
    }

}
