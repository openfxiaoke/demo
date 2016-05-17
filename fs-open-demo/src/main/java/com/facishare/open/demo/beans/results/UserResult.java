package com.facishare.open.demo.beans.results;

import java.util.List;

import com.google.common.base.MoreObjects;

public class UserResult extends BaseResult {

    private static final long serialVersionUID = 317478455338380533L;

    /**
     * openUserId
     */
    private String openUserId;

    /**
     * 成员昵称
     */
    private String name;

    /**
     * 是否停用
     */
    private boolean isStop;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 性别
     */
    private String gender;

    /**
     * 职位
     */
    private String position;

    /**
     * 头像url
     */
    private String profileImageUrl;

    /**
     * 所在部门Id列表
     */
    private List<Integer> departmentIds;

    public String getOpenUserId() {
        return openUserId;
    }

    public void setOpenUserId(String openUserId) {
        this.openUserId = openUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStop() {
        return isStop;
    }

    public void setStop(boolean isStop) {
        this.isStop = isStop;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public List<Integer> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Integer> departmentIds) {
        this.departmentIds = departmentIds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("errorMessage", errorMessage)
                .add("openUserId", openUserId)
                .add("name", name)
                .add("isStop", isStop)
                .add("mobile", mobile)
                .add("email", email)
                .add("gender", gender)
                .add("position", position)
                .add("profileImageUrl", profileImageUrl)
                .add("departmentIds", departmentIds)
                .toString();
    }

}
