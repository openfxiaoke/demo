package com.facishare.open.third.beans.args;

import java.io.Serializable;

/**
 * 封装获取appToken 请求参数的JaveBean
 * @author huanghp
 * @date 2015年8月28日
 */

public class AppTokenArg implements Serializable {
	
	private static final long serialVersionUID = -5691676776742443894L;

	/**
	 * 应用ID
	 */
	private String appId;

	/**
	 * 应用秘钥
	 */
	private String appSecret;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	
}
