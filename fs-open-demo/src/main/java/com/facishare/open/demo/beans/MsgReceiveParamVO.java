package com.facishare.open.demo.beans;

import java.io.Serializable;

/**
 * 封装接收开平推送消息请求参数的JavaBean
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public class MsgReceiveParamVO implements Serializable {

    private static final long serialVersionUID = 3966976690051895927L;

    /**
     * 请求随机数
     */
    private String nonce;

    /**
     * 请求时间戳
     */
    private String timeStamp;

    /**
     * AesKey 加密的消息内容
     */
    private String content;

    /**
     * 请求参数+token 签名
     */
    private String sig;

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSig() {
        return sig;
    }

    public void setSig(String sig) {
        this.sig = sig;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

}
