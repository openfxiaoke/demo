package com.facishare.open.demo.exception;

/**
 * @author huanghp
 * @date 2015年8月28日
 */
@SuppressWarnings("serial")
public class BaseException extends Exception {

    /**
     * 异常代码
     */
    private int code;

    /**
     * 异常信息
     */
    private String msg;

    public BaseException(int code, String msg) {
        super(code + ":" + msg);
        this.code = code;
        this.msg = msg;
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(int code, String msg, Throwable cause) {
        super(code + ":" + msg, cause);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
