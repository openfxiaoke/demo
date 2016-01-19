package com.facishare.open.third.beans.results;

import java.io.Serializable;

public class BindAccountResult implements Serializable {

	private static final long serialVersionUID = -3149116924450935088L;

	/**
	 * 返回码
	 */
	private int errorCode=99;
	
	/**
	 * 开放平台派发的用户账号
	 */
	private String openUserId;
	
	/**
	 * 开放平台派发的公司账号
	 */
	private String corpId;
	
	/**
	 * 对返回码的文本描述内容
	 */
	private String errorMessage;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getOpenUserId() {
		return openUserId;
	}

	public void setOpenUserId(String openUserId) {
		this.openUserId = openUserId;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
