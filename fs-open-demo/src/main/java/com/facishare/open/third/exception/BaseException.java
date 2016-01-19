package com.facishare.open.third.exception;


/**
 * @author huanghp
 * @date 2015年8月28日
 */

@SuppressWarnings("serial")
public class BaseException extends Exception {
	
	/**
	 * 异常代码
	 */
	private String code ;
	
	/**
	 * 异常信息
	 */
	private String msg ;
	
	public BaseException() {
		code = "0";
		msg = "操作成功";
    }
	
    public BaseException(String message) {
    	super(message);
    }

	public BaseException(String code, String msg) {
		super(code + ":" +msg);
		this.code = code;
        this.msg = msg;
	}

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String code, String msg, Throwable cause) {
        super(code + ":" +msg, cause);
        this.code = code;
        this.msg = msg;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
