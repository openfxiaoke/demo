package com.facishare.open.third.manager.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 读取Properties配置信息的类
 * @author huanghp
 * @date 2015年8月28日
 */

@Service("configManager")
public class ConfigManagerImpl {

	/**
	 * 应用Id
	 */
	@Value("${appId}")
	private String appId;

	/**
	 * 应用秘钥
	 */
	@Value("${appSecret}")
	private String appSecret;

	/**
	 * 永久授权码
	 */
	@Value("${permanentCode}")
	private String permanentCode;

	/**
	 * App 约定加密Key
	 */
	@Value("${token}")
	private String token;

	/**
	 * App 约定加密AesKey
	 */
	@Value("${encodingAesKey}")
	private String encodingAesKey;
	
	/**
	 * 重定向uri
	 */
	@Value("${redirectUri}")
	private String redirectUri;
	
	/**
	 * 绑定请求uri
	 */
	@Value("${bindUri}")
	private String bindUri;

	public String getAppId() {
		return appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public String getPermanentCode() {
		return permanentCode;
	}

	public void setPermanentCode(String permanentCode) {
		this.permanentCode = permanentCode;
	}

	public String getToken() {
		return token;
	}

	public String getEncodingAesKey() {
		return encodingAesKey;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public String getBindUri() {
		return bindUri;
	}
	
}
