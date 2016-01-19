package com.facishare.open.third.exception;


/**
 * @author huanghp
 * @date 2015年8月28日
 */

public class RequestParamaterException extends BaseException {
	
	private static final long serialVersionUID = 6389476804584767616L;

	public RequestParamaterException() {
        super();
    }
    
    public RequestParamaterException(String msg) {
    	super(msg);
    }

    public RequestParamaterException(String code,String msg) {
        super(code,msg);
    }

    public RequestParamaterException(Throwable cause) {
        super(cause);
    }

    public RequestParamaterException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }
    
}
