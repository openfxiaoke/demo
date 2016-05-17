package com.facishare.open.demo.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.facishare.open.demo.exception.AesException;

/**
 * 加解密工具类
 * 
 * @author huanghp
 * @date 2015年8月28日
 */
public class SigUtils {

    private static final Logger LOG = LoggerFactory.getLogger(SigUtils.class);

    private SigUtils() {}

    /**
     * @param hexStr
     * @return byte
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }

        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }

        return result;
    }


    /**
     * 还原4个字节的网络字节序
     * 
     * @param orderBytes
     * @return
     */
    private static int recoverNetworkBytesOrder(byte[] orderBytes) {
        int sourceNumber = 0;
        for (int i = 0; i < 4; i++) {
            sourceNumber <<= 8;
            sourceNumber |= orderBytes[i] & 0xff;
        }
        return sourceNumber;
    }

    /**
     * 对密文进行解密.
     * 
     * @param text 需要解密的密文
     * @return 解密得到的明文
     * @throws AesException aes解密失败
     */
    public static String decrypt(String text, String encodingAesKey) throws AesException {
        byte[] original;
        try {
            // 设置解密模式为AES的CBC模式
            byte[] aesKey = Base64.decodeBase64(encodingAesKey + "=");
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec key_spec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
            cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);

            // 使用BASE64对密文进行解码
            byte[] encrypted = Base64.decodeBase64(text);

            // 解密
            original = cipher.doFinal(encrypted);
        } catch (Exception e) {
            LOG.error("decrypt error, details:", e);
            throw new AesException(AesException.DECRYPT_AES_ERROR);
        }

        String plaintext = null;

        try {
            // 去除补位字符
            byte[] bytes = PKCS7Encoder.decode(original);

            // 分离16位随机字符串,网络字节序
            byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);

            int plainTextLength = recoverNetworkBytesOrder(networkOrder);

            plaintext = new String(Arrays.copyOfRange(bytes, 20, 20 + plainTextLength), Charset.forName("utf-8"));

        } catch (Exception e) {
            LOG.error("illegal buffer, details:", e);
            throw new AesException(AesException.ILLEGAL_BUFFER);
        }
        return plaintext;
    }

    /**
     * 用SHA1算法生成安全签名
     * 
     * @param token 票据
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @param encrypt 密文
     * @return 安全签名
     * @throws NoSuchAlgorithmException
     * @throws AesException
     */
    public static String getSHA1(String token, String timestamp, String nonce, String encrypt) throws Exception {
        String[] array = new String[] {token, timestamp, nonce, encrypt};
        StringBuilder sb = new StringBuilder();

        // 字符串排序
        Arrays.sort(array);
        for (int i = 0; i < 4; i++) {
            sb.append(array[i]);
        }
        String str = sb.toString();

        // SHA1签名生成
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(str.getBytes());
        byte[] digest = md.digest();

        StringBuilder hexstr = new StringBuilder();
        String shaHex = "";

        for (int i = 0; i < digest.length; i++) {
            shaHex = Integer.toHexString(digest[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexstr.append(0);
            }
            hexstr.append(shaHex);
        }

        return hexstr.toString();
    }

    /**
     * 解密如出现异常，请查看 <开平API文档的接收消息时的加解密处理>章节
     * 
     * @param decode 密文
     * @param keyt 密钥
     * @return 明文
     */
    public static String decodeAES(String decode, String keyt) {
        String plaintext = null;

        try {
            plaintext = SigUtils.decrypt(decode, keyt);
        } catch (Exception e) {
            LOG.error("decodeAES error, details:", e);
        }

        return plaintext;
    }

}
