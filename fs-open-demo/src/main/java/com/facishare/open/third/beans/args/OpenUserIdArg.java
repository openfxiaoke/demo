package com.facishare.open.third.beans.args;

import java.io.Serializable;

/**
 * 封装通过code获取 openUserId 请求参数的JaveBean
 * @author huanghp
 * @date 2015年8月28日
 */

public class OpenUserIdArg implements Serializable {
	
	private static final long serialVersionUID = 7019560397749052017L;

	/**
	 * appAccessToken
	 */
	private String appAccessToken;
	
	/**
	 * 临时身份票据
	 */
	private String code;

	public String getAppAccessToken() {
		return appAccessToken;
	}

	public void setAppAccessToken(String appAccessToken) {
		this.appAccessToken = appAccessToken;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
