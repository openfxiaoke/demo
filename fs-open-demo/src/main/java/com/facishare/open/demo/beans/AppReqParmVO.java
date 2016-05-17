package com.facishare.open.demo.beans;

import java.io.Serializable;

/**
 * 封装接收App端请求传入参数的JavaBean
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public class AppReqParmVO implements Serializable {

    private static final long serialVersionUID = -1206184202179044275L;

    /**
     * App 端 请求传入的身份态
     */
    private String code;

    /**
     * 请求时间戳
     */
    private String timestamp;

    /**
     * 请求随机数
     */
    private String nonce;

    /**
     * 请求参数+token的签名
     */
    private String codeSig;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getCodeSig() {
        return codeSig;
    }

    public void setCodeSig(String codeSig) {
        this.codeSig = codeSig;
    }

}
