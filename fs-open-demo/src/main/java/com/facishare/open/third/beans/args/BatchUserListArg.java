package com.facishare.open.third.beans.args;

import java.io.Serializable;
import java.util.List;

/**
 * 封装批量获取部门下人员列表 请求参数的JaveBean
 * @author huanghp
 * @date 2015年8月28日
 */

public class BatchUserListArg implements Serializable {

	private static final long serialVersionUID = -6444321202791679719L;

	/**
	 * corpAccessToken
	 */
	private String corpAccessToken;
	
	/**
	 * corpId
	 */
	private String corpId;
	
	/**
	 * 部门ID列表
	 */
	private List<Integer> departmentIds;

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
	
	public List<Integer> getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(List<Integer> departmentIds) {
		this.departmentIds = departmentIds;
	}
	
}
