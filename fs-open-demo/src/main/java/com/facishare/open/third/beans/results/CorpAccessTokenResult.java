package com.facishare.open.third.beans.results;

import java.io.Serializable;

/**
 * 封装获取到的CorpAccessToken结果的JavaBean
 * @author huanghp
 * @date 2015年8月28日
 */

public class CorpAccessTokenResult implements Serializable {

	private static final long serialVersionUID = -4995204525912210225L;

	/**
	 * corpAccessToken
	 */
	private String corpAccessToken;

	/**
	 * corpId
	 */
	private String corpId;

	/**
	 * corpAccessToken 超时时间(单位为：分钟)
	 */
	private long expiresIn;

	/**
	 * 返回结果信息
	 */
	private String errorMessage;

	/**
	 * 结果返回码（0表示成功）
	 */
	private int errorCode;

	public String getCorpAccessToken() {
		return corpAccessToken;
	}

	public void setCorpAccessToken(String corpAccessToken) {
		this.corpAccessToken = corpAccessToken;
	}

	public String getCorpId() {
		return corpId;
	}

	public void setCorpId(String corpId) {
		this.corpId = corpId;
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
