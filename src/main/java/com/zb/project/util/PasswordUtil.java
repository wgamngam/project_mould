package com.zb.project.util;

import com.alibaba.druid.util.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author Hover Ruan
 */
public class PasswordUtil {
    private static Logger log = LoggerFactory.getLogger("HashUtil");

    private static List<String> supportedMethod = Arrays.asList("md5", "sha256");

    public static String generateSalt() {
        return UUID.randomUUID().toString();
    }

    public static String encode(String source) {
        return encodeSha256Hex(source);
    }

    public static String encodeSha256Hex(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(source.getBytes("UTF-8"));

            return Hex.encodeHexString(md.digest());
        } catch (Exception e) {
            log.error("Failed encoding password: " + e.getMessage());
            return null;
        }
    }


    public static final String encodeMD5Hex(String source) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source.getBytes("UTF-8"));

            return Hex.encodeHexString(md.digest());
        } catch (Exception e) {
            log.error("Failed encoding password: " + e.getMessage());
            return null;
        }
    }

    public static String encode(String method, String source) {
        if ("md5".equalsIgnoreCase(method)) {
            return PasswordUtil.encodeMD5Hex(source);
        } else if ("sha256".equalsIgnoreCase(method)) {
            return PasswordUtil.encodeSha256Hex(source);
        }
        return null;
    }

    public static boolean isSupportMethod(String signatureMethod) {
        return supportedMethod.contains(signatureMethod.toLowerCase());
    }

    public static String aesDecrypt(String secretKey, String str) {
        try {
            byte[] byteMi = Base64.base64ToByteArray(str);
            SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decryptedData = cipher.doFinal(byteMi);
            return new String(decryptedData, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String aesEncrypt(String secretKey, String str) {
        try {
            SecretKeySpec key = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] decryptedData = cipher.doFinal(str.getBytes());
            return Base64.byteArrayToBase64(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * AES-128-CBC加密模式时,需要用到一个16位的向量
     */
    public static final String ivParameter = "FRJ9op4MKgTvfBEA";


    /**
     * AES-128-CBC加密.
     *
     * @param secretKey 密钥
     * @param source    需要加密的字符串
     * @return
     */
    public static String aesCbcEncrypt(String secretKey, String source) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = secretKey.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);
            byte[] encrypted = cipher.doFinal(source.getBytes("utf-8"));
            return Base64.byteArrayToBase64(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    /**
     * AES-128-CBC解密.
     *
     * @param secretKey 密钥
     * @param encrypted 需要解密的字符串
     * @return
     */
    public static String aesCbcDecrypt(String secretKey, String encrypted) {
        try {
            byte[] raw = secretKey.getBytes("ASCII");
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            byte[] encrypted1 = Base64.base64ToByteArray(encrypted);
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, "utf-8");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
