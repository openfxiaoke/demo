package com.facishare.open.demo.beans.results;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 5753982789557686887L;

    private Integer code = 0;

    private String msg = "成功";

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
