package com.facishare.open.demo.beans.results;

import java.util.List;

import com.google.common.base.MoreObjects;

/**
 * 封装获取到的部门下人员列表结果 的JaveBean
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public class DeptUserListResult extends BaseResult {

    private static final long serialVersionUID = 2273578171263471617L;

    /**
     * 人员列表 @see User
     */
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("errorMessage", errorMessage)
                .add("userList", userList)
                .toString();
    }

}
