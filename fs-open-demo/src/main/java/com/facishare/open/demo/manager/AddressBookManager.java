package com.facishare.open.demo.manager;

import com.facishare.open.demo.beans.results.CorpUserMapResult;
import com.facishare.open.demo.beans.results.Department;
import com.facishare.open.demo.beans.results.DeptAddResult;
import com.facishare.open.demo.beans.results.DeptListResult;
import com.facishare.open.demo.beans.results.DeptUpdateResult;
import com.facishare.open.demo.beans.results.UserResult;
import com.facishare.open.demo.exception.AccessTokenException;

/**
 * 通讯录数据的管理接口
 * 
 * @author huanghp
 * @date 2015年9月18日
 */
public interface AddressBookManager {

    /**
     * 获取公司所有人员信息的方法
     * 
     * @return
     */
    public CorpUserMapResult getCorpEmployeeMap() throws AccessTokenException;

    /**
     * 添加部门
     * 
     * @param department 部门信息
     * @return
     * @throws AccessTokenException
     */
    public DeptAddResult addDept(Department department) throws AccessTokenException;

    /**
     * 修改部门
     * 
     * @param department 部门信息
     * @return
     * @throws AccessTokenException
     */
    public DeptUpdateResult modifyDept(Department department) throws AccessTokenException;

    /**
     * 获取部门列表
     * 
     * @return
     * @throws AccessTokenException
     */
    public DeptListResult getDeptList() throws AccessTokenException;

    /**
     * 获取成员详细信息
     * 
     * @param openUserId
     * @return
     * @throws AccessTokenException
     */
    public UserResult getUserInfo(String openUserId) throws AccessTokenException;

}
