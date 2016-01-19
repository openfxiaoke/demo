package com.facishare.open.third.beans.emun;

public enum RecMessageType {
	
	/**
	 * 身份态枚举
	 */
	CODE("Code"),
	
	/**
	 * 永久授权码
	 */
	PERMANENTCODE("PermanentCode"),
	
	/**
	 * 取消永久授权码
	 */
	CANCLEPERMANENTCODE("CancelPermanentCode"),
	
	/**
	 * 文本或者事件消息
	 */
	MESSAGE("Message"),
	
	;
	
	private String typeName;

	private RecMessageType(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
