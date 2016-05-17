package com.facishare.open.demo.beans.results;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhongcy on 2016/4/14.
 */
public class User implements Serializable {

    private static final long serialVersionUID = -3144246502176518299L;

    /**
     * 用户登录账号
     */
    private String account;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户姓名
     */
    private String fullName;

    /**
     * 职位
     */
    private String position;

    /**
     * 性别 M：男 　F：女 UNKNOWN：未知
     */
    private String gender;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * Email
     */
    private String email;

    /**
     * 开平用户账号
     */
    private String openUserId;

    /**
     * qq
     */
    private String qq;

    /**
     * 微信
     */
    private String weixin;

    /**
     * 所属部门列表
     */
    private List<Integer> departmentIds;

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

    public String getOpenUserId() {
        return openUserId;
    }

    public void setOpenUserId(String openUserId) {
        this.openUserId = openUserId;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
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
                .add("account", account)
                .add("password", password)
                .add("name", name)
                .add("fullName", fullName)
                .add("position", position)
                .add("gender", gender)
                .add("mobile", mobile)
                .add("email", email)
                .add("openUserId", openUserId)
                .add("qq", qq)
                .add("weixin", weixin)
                .add("departmentIds", departmentIds)
                .toString();
    }

}
