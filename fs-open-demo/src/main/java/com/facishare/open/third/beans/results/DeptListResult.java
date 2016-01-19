package com.facishare.open.third.beans.results;

import java.io.Serializable;
import java.util.List;

/**
 * 封装获取到的部门列表结果 的JaveBean
 * @author huanghp
 * @date 2015年8月28日
 */

public class DeptListResult implements Serializable {

	private static final long serialVersionUID = -4407072730712353168L;

	/**
	 * 部门列表 @see DepartmentResult
	 */
	private List<DepartmentResult> departments;

	/**
	 * 结果返回码（0表示成功）
	 */
	private int errorCode = 99;

	/**
	 * 返回结果信息
	 */
	private String errorMessage;

	public List<DepartmentResult> getDepartments() {
		return departments;
	}

	public void setDepartments(List<DepartmentResult> departments) {
		this.departments = departments;
	}

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
	
}
