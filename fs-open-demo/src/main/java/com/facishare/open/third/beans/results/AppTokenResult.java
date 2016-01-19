package com.facishare.open.third.beans.results;

import java.io.Serializable;

/**
 * 封装获取到的AppToken结果的JavaBean
 * @author huanghp
 * @date 2015年8月28日
 */

public class AppTokenResult implements Serializable {

	private static final long serialVersionUID = 7698338379605078704L;

	/**
	 * appAccessToken
	 */
	private String appAccessToken;

	/**
	 * appToken 超时时间(单位为：分钟)
	 */
	private long expiresIn;

	/**
	 * 返回结果信息
	 */
	private String errorMessage;

	/**
	 * 结果返回码（0表示成功）
	 */
	private int errorCode = 99;

	public String getAppAccessToken() {
		return appAccessToken;
	}

	public void setAppAccessToken(String appAccessToken) {
		this.appAccessToken = appAccessToken;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	
}
