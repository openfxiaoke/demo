package com.facishare.open.third.beans.args;

import java.io.Serializable;

/**
 * 封装获取成员信息 请求参数的JaveBean
 * @author huanghp
 * @date 2015年8月28日
 */

public class UserInfoArg implements Serializable {

	private static final long serialVersionUID = 8596610310375967604L;
	
	/**
	 * corpAccessToken
	 */
	private String corpAccessToken;
	
	/**
	 * corpId
	 */
	private String corpId;
	
	/**
	 * openUserId
	 */
	private String openUserId;

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

	public String getOpenUserId() {
		return openUserId;
	}

	public void setOpenUserId(String openUserId) {
		this.openUserId = openUserId;
	}
	
}
