package com.facishare.open.third.exception;


/**
 * @author huanghp
 * @date 2015年8月28日
 */

@SuppressWarnings("serial")
public class SYSException extends BaseException {
    
    public SYSException() {
    	super();
    }
    
    public SYSException(String msg) {
    	super(msg);
    }

    public SYSException(String code,String msg) {
        super(code,msg);
    }

    public SYSException(Throwable cause) {
        super(cause);
    }

    public SYSException(String code, String msg, Throwable cause) {
        super(code, msg, cause);
    }

}
