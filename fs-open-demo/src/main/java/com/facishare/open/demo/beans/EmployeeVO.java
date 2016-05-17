package com.facishare.open.demo.beans;

import java.io.Serializable;

/**
 * 员工信息
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public class EmployeeVO implements Serializable {

    private static final long serialVersionUID = -2546602872872833513L;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户姓名
     */
    private String fullName;

    /**
     * openUserId(开放平台UserId)
     */
    private String openId;

    /**
     * 职位
     */
    private String position;

    /**
     * 部门 多部门用,隔开如(部门1,部门2)
     */
    private String department;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 性别
     */
    private String gender;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openID) {
        this.openId = openID;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
