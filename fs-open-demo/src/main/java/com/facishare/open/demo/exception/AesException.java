package com.facishare.open.demo.exception;

/**
 * @author 黄虎平
 * @date 2015年9月1日
 */
@SuppressWarnings("serial")
public class AesException extends BaseException {

    public static final int OK = 0;

    public static final int VALIDATE_SIGNATURE_ERROR = -40001;

    public static final int PARSE_XML_ERROR = -40002;

    public static final int COMPUTE_SIGNATURE_ERROR = -40003;

    public static final int ILLEGAL_AES_KEY = -40004;

    public static final int ENCRYPT_AES_ERROR = -40006;

    public static final int DECRYPT_AES_ERROR = -40007;

    public static final int ILLEGAL_BUFFER = -40008;
    
    public AesException(int code) {
        super(code, getMessage(code));
    }

    private static String getMessage(int code) {
        switch (code) {
            case VALIDATE_SIGNATURE_ERROR:
                return "签名验证错误";
            case PARSE_XML_ERROR:
                return "xml解析失败";
            case COMPUTE_SIGNATURE_ERROR:
                return "sha加密生成签名失败";
            case ILLEGAL_AES_KEY:
                return "SymmetricKey非法";
            case ENCRYPT_AES_ERROR:
                return "aes加密失败";
            case DECRYPT_AES_ERROR:
                return "aes解密失败";
            case ILLEGAL_BUFFER:
                return "解密后得到的buffer非法";
            default:
                return null; // cannot be
        }
    }

}
