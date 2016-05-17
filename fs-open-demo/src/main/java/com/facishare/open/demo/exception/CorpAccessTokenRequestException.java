package com.facishare.open.demo.exception;

@SuppressWarnings("serial")
public class CorpAccessTokenRequestException extends AccessTokenException {
    
    public CorpAccessTokenRequestException(int code, String msg) {
        super(code, msg);
    }
    
}
