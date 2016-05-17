package com.facishare.open.demo.beans.args;

import com.google.common.base.MoreObjects;

import java.util.List;

/**
 * Created by zhongcy on 2016/4/14.
 */
public class UserAddArg implements Arg {

    private static final long serialVersionUID = -249943850515566854L;

    /**
     * 应用ID
     */
    private String appId;

    /**
     * 执行操作的员工ID
     */
    private String currentUserId;

    /**
     * 员工登录账号
     */
    private String account;

    /**
     * 员工登录密码
     */
    private String password;

    /**
     * 员工昵称
     */
    private String name;

    /**
     * 员工姓名
     */
    private String fullName;

    /**
     * 职位
     */
    private String position;

    /**
     * 性别
     */
    private String gender;

    /**
     * 手机
     */
    private String mobile;

    /**
     * email
     */
    private String email;

    /**
     * 所属部门列表
     */
    private List<Integer> departmentIds;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Integer> departmentIds) {
        this.departmentIds = departmentIds;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("appId", appId)
                .add("currentUserId", currentUserId)
                .add("account", account)
                .add("password", password)
                .add("name", name)
                .add("fullName", fullName)
                .add("position", position)
                .add("gender", gender)
                .add("mobile", mobile)
                .add("email", email)
                .add("departmentIds", departmentIds)
                .toString();
    }
}
