package com.facishare.open.third.beans.results;

import java.io.Serializable;

/**
 * 部门实体类
 * @author huanghp
 * @date 2015年9月01日
 */

public class DepartmentResult implements Serializable{

	private static final long serialVersionUID = 1044105896400373106L;
	
	/**
	 * 部门Id
	 */
	private int id;
	
	/**
	 * 部门名称
	 */
	private String name;
	
	/**
	 * 父部门Id
	 */
	private int parentId;
	
	/**
	 * 部门排序号
	 */
	private int order;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getParentId() {
		return parentId;
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}
	
}
