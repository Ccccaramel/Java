package com.ding.hyld.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AesUtils {
    //可配置到Constant中，并读取配置文件注入,16位,自己定义
    public static final String KEY = "AESECryptKeyDemo";

    //参数分别代表 算法名称/加密模式/数据填充方式
    private static final String ALGORITHMSTR = "AES";

    /* 加密
     * @param content 加密的字符串
     * @param encryptKey key值*/
    public static String encrypt(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        byte[] b = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        // 采用base64算法进行转码,避免出现中文乱码
        return Base64.encodeBase64String(b);
    }

    /* 解密
     * @param encryptStr 解密的字符串
     * @param decryptKey 解密的key值
     *
     * */
    public static String decrypt(String encryptStr, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        // 采用base64算法进行转码,避免出现中文乱码
        byte[] encryptBytes = Base64.decodeBase64(encryptStr);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static String encrypt(String content) throws Exception {
        return encrypt(content, KEY);
    }

    public static String decrypt(String encryptStr) throws Exception {
        return decrypt(encryptStr, KEY);
    }

    public static void main(String[] args) throws Exception {
        String str = "{" +
                "id:1" +
                ", userId:2" +
                ", userName:''" +
                ", ip:'127.0.0.1'" +
                ", address:'china'" +
                ", trueAddress:'wuhan'" +
                ", note:'test'" +
                ", startDate:'2020-01-01T10:10:10'" +
                ", endDate:'2020-01-01T10:10:10'" +
                ", time:'2020-01-01T10:10:10'" +
                '}';
        String data = encrypt(str);
        System.out.println("加密:"+data);
        System.out.println("解密:"+decrypt(data));
    }
}

