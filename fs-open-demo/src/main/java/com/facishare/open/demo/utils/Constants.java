package com.facishare.open.demo.utils;

public class Constants {

    public static final String SESSION_CURRENT_OPEN_USER_ID = "currentOpenUserId";

    private Constants() {}

    /**
     * 接口返回码
     * 
     * @author gaoshengbo
     *
     */
    public enum interfaceResponseCode {

        APP_ACCESS_TOKEN_EXPIRED(20005, "appAccessToken不存在或者已经过期"),

        CORP_ACCESS_TOKEN_EXPIRED(20016, "corpAccessToken不存在或者已经过期");

        public int code;

        public String msg;

        private interfaceResponseCode(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    /**
     * 异常码定义
     * 
     * @author gaoshengbo
     *
     */
    public enum interfaceException {

        ILLEGAL_ARGUMENT(-8, "参数不合法"),

        INTERFACE_EXCEPTION(-9, "调用接口失败");

        public int code;

        public String msg;

        private interfaceException(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

}
