package com.facishare.open.demo.beans;

import java.io.Serializable;

public class CorpAccessToken implements Serializable {

    private static final long serialVersionUID = -4995204525912210225L;

    /**
     * corpAccessToken
     */
    private String corpAccessToken;

    /**
     * corpId
     */
    private String corpId;

    public String getCorpAccessToken() {
        return corpAccessToken;
    }

    public void setCorpAccessToken(String corpAccessToken) {
        this.corpAccessToken = corpAccessToken;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

}
