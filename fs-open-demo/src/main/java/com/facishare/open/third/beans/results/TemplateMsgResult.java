package com.facishare.open.third.beans.results;

import java.io.Serializable;

/**
 * 封装发送模版消息结果的JavaBean
 * @author huanghp
 * @date 2015年9月02日
 */

public class TemplateMsgResult implements Serializable {
	
	private static final long serialVersionUID = -3847890303278102799L;

	/**
	 * 结果返回码（0表示成功）
	 */
	private int errorCode = 99;
	
	/**
	 * 返回结果信息
	 */
	private String errorMessage;

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
	
}
