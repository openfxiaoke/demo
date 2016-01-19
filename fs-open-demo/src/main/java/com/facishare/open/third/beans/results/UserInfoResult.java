package com.facishare.open.third.beans.results;

import java.io.Serializable;
import java.util.List;

/**
 * 封装获取到的成员信息的JavaBean
 * @author huanghp
 * @date 2015年8月28日
 */

public class UserInfoResult implements Serializable {

	private static final long serialVersionUID = 317478455338380533L;
	
	/**
	 * openUserId
	 */
	private String openUserId;
	
	/**
	 * 成员名称
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
	 * 人员Email
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
	 * 头像id
	 */
	private String profileImageUrl;
	

	/**
	 * 所在部门Id列表
	 */
	private List<Integer> departmentIds;

	/**
	 * 结果返回码（0表示成功）
	 */
	private int errorCode;

	/**
	 * 返回结果信息
	 */
	private String errorMessage;

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

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
	
}
