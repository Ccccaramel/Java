package com.ding.hyld.utils;

import com.alibaba.fastjson.JSON;
import com.ding.hyld.vo.VisitLogVo;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * 数据加密与解密
 * Cipher 提供加解密 API
 * 其中 RSA 非对称加密解密内容长度是有限制的
 * 加密长度不超过 117Byte
 * 解密长度不超过 128Byte
 */
@Component
public class RsaUtils {
    protected static final Log log = LogFactory.getLog(RsaUtils.class);
    public static final String KEY_RSA_TYPE = "RSA";
    public static final String KEY_RSA_TYPE_ALL = "RSA/ECB/PKCS1Padding";
    public static final int KEY_SIZE = 1024; // JDK 方式 RSA 加密最大只有 1024 位
    private static final int ENCODE_PART_SIZE = KEY_SIZE/8;
    public static final String PUBLIC_KEY_NAME = "public";
    public static final String PRIVATE_KEY_NAME = "private";

    public static String PUBLIC_KEY = "HuangYeLuanDou";
    public static String PRIVATE_KEY = "Ding";

    public static final RsaKeyPair rsaKeyPair = new RsaKeyPair();

    // ******************************************

    /**
     * 公钥加密
     *
     * @param publicKeyString 公钥
     * @param text 待加密的文本
     * @return 加密后的文本
     */
    public static String encryptByPublicKey(String publicKeyString, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec2);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.encodeBase64String(result);
    }

    /**
     * 公钥解密
     * @param publicKeyString 公钥
     * @param text 待解密的信息
     * @return 解密后的文本
     */
    public static String decryptByPublicKey(String publicKeyString, String text) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] result = cipher.doFinal(Base64.decodeBase64(text));
        return new String(result);
    }

    /**
     * 私钥加密
     * @param privateKeyString 私钥
     * @param text 待加密的信息
     * @return 加密后的文本
     */
    public static String encryptByPrivateKey(String privateKeyString, String text) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] result = cipher.doFinal(text.getBytes());
        return Base64.encodeBase64String(result);
    }

    /**
     * 私钥解密
     * @param privateKeyString 私钥
     * @param data 待解密的文本
     * @return 解密后的文本
     */
    public static String decryptByPrivateKey(String privateKeyString, String data) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        byte[] result = cipher.doFinal(Base64.decodeBase64(data));
//        return new String(result);
        return new String(getMaxResultDecrypt(escape(data), cipher));
    }

    /**
     * 分段解密
     * @param str
     * @param cipher
     * @return
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getMaxResultDecrypt(String str, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] inputArray = Base64.decodeBase64(str.getBytes("UTF-8"));
        int inputLength = inputArray.length;
        // 最大解密字节数，超出最大字节数需要分组加密
        int MAX_ENCRYPT_BLOCK = 128;
        // 标识
        int offSet = 0;
        byte[] resultBytes = {};
        byte[] cache = {};
        while (inputLength - offSet > 0) {
            if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                System.out.println("provider:" + cipher.getProvider().getClass().getName());
                cache = cipher.doFinal(inputArray, offSet, MAX_ENCRYPT_BLOCK);
                offSet += MAX_ENCRYPT_BLOCK;
            } else {
                cache = cipher.doFinal(inputArray, offSet, inputLength - offSet);
                offSet = inputLength;
            }
            resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
            System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
        }
        return resultBytes;
    }

    /**
     * 私钥解密
     * @param data 待解密的文本
     * @return 解密后的文本
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data) throws Exception{
        return decryptByPrivateKey(rsaKeyPair.getPrivateKey(),data);
    }

    /**
     * 获取私钥对象
     * @param privateKeyBase64
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey getPrivateKey(String privateKeyBase64)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec privatekcs8KeySpec =
                new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyBase64));
        PrivateKey privateKey = keyFactory.generatePrivate(privatekcs8KeySpec);
        return privateKey;
    }


    /**
     * 构建RSA密钥对
     * @return 生成后的公私钥信息
     */
    @Bean
    public void generateKeyPair() throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024,new SecureRandom());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        String publicKeyString = Base64.encodeBase64String(rsaPublicKey.getEncoded());
        String privateKeyString = Base64.encodeBase64String(rsaPrivateKey.getEncoded());
        rsaKeyPair.setPrivateKey(privateKeyString);
        rsaKeyPair.setPublicKey(publicKeyString);
        PUBLIC_KEY = publicKeyString;
        PRIVATE_KEY = privateKeyString;
    }

    public static class RsaKeyPair{
        private String publicKey;
        private String privateKey;

        public RsaKeyPair() {
        }

        public RsaKeyPair(String publicKey, String privateKey) {
            this.publicKey = publicKey;
            this.privateKey = privateKey;
        }

        public String getPublicKey() {
            return publicKey;
        }

        public void setPublicKey(String publicKey) {
            this.publicKey = publicKey;
        }

        public String getPrivateKey() {
            return privateKey;
        }

        public void setPrivateKey(String privateKey) {
            this.privateKey = privateKey;
        }
    }

    public static String getPublicKeyStr() {
        return PUBLIC_KEY;
    }

    public static String getPrivateKeyStr() {
        return PRIVATE_KEY;
    }

    public static RsaKeyPair getRsaKeyPair(){
        return rsaKeyPair;
    }

    /**
     * 转义,数据从前端传入到后端时自动被转义,所以要转回来,不转报错
     * @param data
     * @return
     */
    public static String escape(String data){
        return data.replaceAll(" ","+")
                .replaceAll("%2F","/")
                .replaceAll("%3F","?")
                .replaceAll("%25","%")
                .replaceAll("%26","&")
                .replaceAll("%23","#")
                .replaceAll("%20"," ")
                .replaceAll("%3D","=");
    }

    public static void main(String[] args) {
        String pri ="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBALZeMdxkTET4DKj2UzdODBp0WmmIp/CbeJ5jEWjV8X6T0daEnDSA6itpH8iYlv63CNsBNdf5BrZGLvNmvRp7jOYgU6a7haT/PLXUU4psLz6YMbOWGWUUuw6RcN+GmOPuaJrq/vPbIuG+CpdTGwsb5iM/sxWYCYljonARLVQmMVXLAgMBAAECgYEAp9s3haYD1Y+DB5oCqmr6FPd3qJ+SBJL1iMOi6J3TdDBW+l6A8Iqf/2j5Ske446ZRTlNk3e9m0Cr1WC/+avvBiRgz5ukUsZk5J9DvrgwSLuaiP/ETAKJ2jd/zkN40fmJ3ZuL4IZvQZaOAevD8doPj5CwczsakOcidMEkMP933LzECQQDtp5qKLYwHBX7kPiLB8ORvKp0R8ODZ6UA+2rKuKce77lAPPuDCZ0fF2DyBPIO50qhp4sJpERmoe6HbsUW1cJM/AkEAxHILv0l4X2PUmRYDCx0XynOx9uAIN5BN5nrosITJEn8kRXhD+DDNlYrubt7LSKuhrN9/UFTotlw/zvMttWF2dQJBANucQsmndztRz8V4ycOwKFe16DmY/x8/VRgAHpekkibO/Pppn2bl68QeoyLF2fAQg4J9git3Gt+/h32tXf5hjh8CQAjM8ynGqNXdKxEQo2JEE2TGehqNPtD+bf79Up9V04w4le9my+S6ivtAFh5xbLn8tmmAObvnkeVZjKJA0rNlgmECQQDCyKAWVzj7l+tgY4C6RbavGIi9LZYYZitoXWO+NeucEyNYXmYsIJUMkp2mFHoJguwecbzBsu/9VNlST/znkxzG";
        String data = "Yc9T1Y58zV9pHsekjYhdLenr69CFwLJFDlUVMmFvGoIglCfGGPNu/v9ycO6mYzE9HhYwHY9uAjQE4mDE1wN0cnIb9HxgwIBZwF5wElJQ6owEXYpTGDONU9hj2NcFt uFFZ4S9c4vvKewJmiLB4817Jk AavA5fjvEWHdbQViIKU:";
        try {
            VisitLogVo visitLogVo = JSON.parseObject(decryptByPrivateKey(pri,data), VisitLogVo.class);
            System.out.println("213");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
