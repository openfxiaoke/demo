package com.facishare.open.demo.beans.results;

import java.io.Serializable;

import com.google.common.base.MoreObjects;

/**
 * Created by zhongcy on 2016/4/18.
 */
public class BaseResult implements Serializable {

    private static final long serialVersionUID = -1694441659920313916L;

    /**
     * 结果返回码（0表示成功）
     */
    protected int errorCode = 99;

    /**
     * 返回结果信息
     */
    protected String errorMessage;

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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("errorMessage", errorMessage)
                .toString();
    }

}
