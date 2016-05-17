package com.facishare.open.demo.beans;

/**
 * @author huanghp
 * @date 2015年8月28日
 */
public class ResponseResultVO {

    /**
     * 执行时间
     */
    private String time = "";

    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应内容
     */
    private String rs;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }

}
