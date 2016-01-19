package com.facishare.open.third.beans.results;

import java.io.Serializable;

/**
 * 封装获取到的OpenUserId 结果的JavaBean
 * @author huanghp
 * @date 2015年8月28日
 */

public class OpenUserIdResult implements Serializable {
	
	private static final long serialVersionUID = 7403143650678784355L;

	/**
	 * 结果返回码（0表示成功）
	 */
	private int errorCode = 99;
	
	/**
	 * 返回结果信息
	 */
	private String errorMessage;
	
	/**
	 * openUserId
	 */
	private String openUserId;
	
	/**
	 * corpId
	 */
	private String corpId;

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

}
