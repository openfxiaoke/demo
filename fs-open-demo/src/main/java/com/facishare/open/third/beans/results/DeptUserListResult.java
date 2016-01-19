package com.facishare.open.third.beans.results;

import java.io.Serializable;
import java.util.List;

/**
 * 封装获取到的部门下人员列表结果 的JaveBean
 * @author huanghp
 * @date 2015年8月28日
 */

public class DeptUserListResult implements Serializable {

	private static final long serialVersionUID = 2273578171263471617L;

	/**
	 * 结果返回码（0表示成功）
	 */
	private int errorCode;

	/**
	 * 返回结果信息
	 */
	private String errorMessage;

	/**
	 * 人员列表 @see UserInfoResult
	 */
	private List<UserInfoResult> userList;

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

	public List<UserInfoResult> getUserList() {
		return userList;
	}

	public void setUserList(List<UserInfoResult> userList) {
		this.userList = userList;
	}
	
}
