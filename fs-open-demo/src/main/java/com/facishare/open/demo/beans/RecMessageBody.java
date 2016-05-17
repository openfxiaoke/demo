package com.facishare.open.demo.beans;

/**
 * 接收的消息体
 * 
 * @author huanghp
 * @date 2015-09-17
 */
public class RecMessageBody {

    /**
     * 消息类型
     */
    private String type;

    /**
     * 消息内容
     */
    private String content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
