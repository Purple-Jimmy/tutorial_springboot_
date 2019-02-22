package com.java.encrypt.demo;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author: jimmy
 * @Date: 2019/1/29
 */
public class Hmacsha256Demo {
    public static void main(String[] args) {
       // String str = "{\"sid\": \"e10adc3949ba59abbe56e057f20f883e\",\"msisdn\": \"17849976323\",\"province\": \"江苏\",\"os\": \"Android\",\"pageIndex\": 1,\"pageSize\": 15,\"keyword\": \"喜剧电影\"}";
        String str = "";
        System.out.println(sha256_HMAC(str,"migu123456"));
    }

    /**
     * 将加密后的字节数组转换成字符串
     * @param b 字节数组
     * @return 字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs.append('0');
            }
            hs.append(stmp);
        }
        return hs.toString().toLowerCase();
    }

    /**
     * 加密
     * @param message 消息
     * @param secret  秘钥
     * @return 加密后字符串
     */
    private static String sha256_HMAC(String message, String secret) {
        String hash = "";
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(message.getBytes("UTF-8"));
            hash = byteArrayToHexString(bytes);
        } catch (Exception e) {
            System.out.println("Error HmacSHA256 ===========" + e.getMessage());
        }
        return hash;

    }

}
