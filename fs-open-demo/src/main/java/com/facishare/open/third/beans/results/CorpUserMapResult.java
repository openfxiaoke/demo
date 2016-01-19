package com.facishare.open.third.beans.results;

import java.io.Serializable;
import java.util.Map;

import com.facishare.open.third.beans.EmployeeVO;

/**
 * 封装获取公司所有人员列表结果 的JaveBean
 * @author huanghp
 * @date 2015年9月18日
 */

public class CorpUserMapResult implements Serializable {

	private static final long serialVersionUID = 2273578171263471617L;

	/**
	 * 人员总量
	 */
	private int totalMember;
	
	/**
	 * 部门总量
	 */
	private int totalDepartment;
	
	/**
	 * 结果返回码（0表示成功）
	 */
	private int errorCode;

	/**
	 * 返回结果信息
	 */
	private String errorMessage;

	/**
	 * 人员列表 @see UserInfoResult
	 */
	private Map<String, EmployeeVO> corpUserMap;

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

	public Map<String, EmployeeVO> getCorpUserMap() {
		return corpUserMap;
	}

	public void setCorpUserMap(Map<String, EmployeeVO> corpUserMap) {
		this.corpUserMap = corpUserMap;
	}

	public int getTotalMember() {
		return totalMember;
	}

	public void setTotalMember(int totalMember) {
		this.totalMember = totalMember;
	}

	public int getTotalDepartment() {
		return totalDepartment;
	}

	public void setTotalDepartment(int totalDepartment) {
		this.totalDepartment = totalDepartment;
	}
	
}
