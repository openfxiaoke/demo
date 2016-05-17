package com.facishare.open.demo.beans;

/**
 * 发送http https 请求的返回结果包装类
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public class HttpResponseMessageVO {

    /**
     * http响应状态码
     */
    private String httpCode;

    /**
     * http响应消息
     */
    private String message;

    /**
     * http响应内容
     */
    private String content;

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
