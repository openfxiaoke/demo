package com.facishare.open.third.beans.args;

import java.io.Serializable;

/**
 * 封装获取CorpAccessToken 请求参数的JaveBean
 * @author huanghp
 * @date 2015年8月28日
 */

public class CorpAccessTokenArg implements Serializable {
	
	private static final long serialVersionUID = 119087883828028381L;

	/**
	 * 应用的appAccessToken
	 */
	private String appAccessToken;

	/**
	 * 永久授权码
	 */
	private String permanentCode;

	public String getAppAccessToken() {
		return appAccessToken;
	}

	public void setAppAccessToken(String appAccessToken) {
		this.appAccessToken = appAccessToken;
	}

	public String getPermanentCode() {
		return permanentCode;
	}

	public void setPermanentCode(String permanentCode) {
		this.permanentCode = permanentCode;
	}
	
}
