package com.facishare.open.demo.beans.args;

import com.google.common.base.MoreObjects;

public class BaseArg implements Arg {

    private static final long serialVersionUID = 6280290415792613761L;

    /**
     * 第三方应用获得企业授权的凭证
     */
    protected String corpAccessToken;

    /**
     * 开放平台派发的公司账号
     */
    protected String corpId;

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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("corpAccessToken", corpAccessToken)
                .add("corpId", corpId)
                .toString();
    }

}
