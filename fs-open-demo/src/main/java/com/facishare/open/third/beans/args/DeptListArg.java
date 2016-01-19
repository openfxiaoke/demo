package com.facishare.open.third.beans.args;

import java.io.Serializable;

/**
 * 封装获取部门列表 请求参数的JaveBean
 * @author huanghp
 * @date 2015年8月28日
 */

public class DeptListArg implements Serializable {

	private static final long serialVersionUID = -5486364953254248717L;

	/**
	 * corpAccessToken
	 */
	private String corpAccessToken;
	
	/**
	 * corpId
	 */
	private String corpId;

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

}
