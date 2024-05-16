package com.ding.office.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

// 有待改进!
/**
 * 数据加密与解密
 * Cipher 提供加解密 API
 * 其中 RSA 非对称加密解密内容长度是有限制的
 */
@Slf4j
@Component
public class RsaUtils {
    public static String PUBLIC_KEY = "";
    public static String PRIVATE_KEY = "";
    public static final RsaKeyPair rsaKeyPair = new RsaKeyPair();

    /**
     * 公钥加密
     * @param data
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data) throws Exception {
        return encryptByPublicKey(rsaKeyPair.getPublicKey(),data);
    }

    /**
     * 公钥加密
     */
    public static String encryptByPublicKey(String publicKey, String data) throws Exception {
        X509EncodedKeySpec x509EncodedKeySpec2 = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicK = keyFactory.generatePublic(x509EncodedKeySpec2);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicK);


        int inputLen = data.getBytes().length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offset > 0) {
            if (inputLen - offset > 117) {
                cache = cipher.doFinal(data.getBytes(), offset, 117);
            } else {
                cache = cipher.doFinal(data.getBytes(), offset, inputLen - offset);
            }
            out.write(cache, 0, cache.length);
            i++;
            offset = i * 117;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        // 获取加密内容使用base64进行编码,并以UTF-8为标准转化成字符串
        // 加密后的字符串
        return new String(Base64.encodeBase64(encryptedData));
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
     * 私钥解密
     * @param privateKeyString 私钥
     * @param data 待解密的文本
     * @return 解密后的文本
     */
    public static String decryptByPrivateKey(String privateKeyString, String data) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec5 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyString));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec5);
//        Provider provider = new org.bouncycastle.jce.provider.BouncyCastleProvider();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        log.info("加密(转义前):{}",data);
        return new String(getMaxResultDecrypt(escape(data), cipher), StandardCharsets.UTF_8);
    }

    /**
     * 分段解密
     * @param str
     * @param cipher
     * @return
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     */
    private static byte[] getMaxResultDecrypt(String str, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException {
        log.info("加密(转义后):{}",str);
        log.info("provider:{}" , cipher.getProvider().getClass().getName());
        byte[] inputArray = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
        int inputLength = inputArray.length;
        // 最大解密字节数，超出最大字节数需要分组加密
        int MAX_ENCRYPT_BLOCK = 256; // 与 1 处对应,128*i 对应 1024*i ,i可取任意正整数,但太大也不合适
        // 标识
        int offSet = 0;
        byte[] resultBytes = {};
        byte[] cache = {};
        while (inputLength - offSet > 0) {
            if (inputLength - offSet > MAX_ENCRYPT_BLOCK) {
                log.info("分段");
                cache = cipher.doFinal(inputArray, offSet, MAX_ENCRYPT_BLOCK);
                offSet += MAX_ENCRYPT_BLOCK;
            } else {
                log.info("未分段");
                cache = cipher.doFinal(inputArray, offSet, inputLength - offSet);
                offSet = inputLength;
            }
            resultBytes = Arrays.copyOf(resultBytes, resultBytes.length + cache.length);
            System.arraycopy(cache, 0, resultBytes, resultBytes.length - cache.length, cache.length);
        }
        return resultBytes;
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
        keyPairGenerator.initialize(2048,new SecureRandom()); // 1
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
//                .replaceAll("%3F","?")
                .replaceAll("%25","%")
//                .replaceAll("%26","&")
//                .replaceAll("%23","#")
//                .replaceAll("%20"," ")
                .replaceAll("%3D","=");
    }

    public static void main(String[] args) {
        String pri = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAICJMGsrS3ElL9IS4n+cFVa8wYJPQe/BzAiicA6eCr+53vhMYcxqHd1jNXhVmdwFIVadgqbLlo3ki1hKXbwT/Hn/yivg0ISExmSSTGuEZHV72fy7eQTh7YNP/usLDtR+X6d8o4a0XjXdu9Z4Z3E5ak5BKZIZULewBgemWVKRJVC1AgMBAAECgYBVZ9KuQvQ71/OwaC/rjrB3E7CYf0VWo7SIwDYo4EZQIpbO8SHCi3gQlE0BCg/Tq+z8+mfHoFsHKSaVocQqpjUJfTmzP1HylCdZLixFQZ0blrpnaInl2nRzLv42A6G55hEVwRwywrcrzcelkBRrVCkJpVhZLbtogDWgkA2C1ZQauQJBAN317IWFpdywgcCVmpNOpa+wZYLQfboahphwJ26eMUkEw7DbWRIc206+qfphXhN2m/k6AOEE4Q2Ir1Nkt9e8FmcCQQCUP3OE+mmbt4LSDu59Th+6KVfOfZMoeBQzTI/76fS/yMExKC40Xk9TCi6BI74vD95zzcLHLxPoABL6p6UonxaDAkEAggoetGuErTNMEYSUt1EQiUoTbvyvbGU2DgFHWOudfGYqMGeSW6ZEZnIWK/b3oveOkgmFkk/FeqRl87nCa8ZqaQJAZs0RSFd1wHujBhNAojf4nFlrAE/oKhoKuAARoHj+Nbf68CGS2NHFZCodUYP9sBjrA5bdyM4GmczjXFnAl8iqDQJAIb20B8bv6w7VO34LQO5PXnXvyT2WHcTzbmzQFrLrPBXIluZwLgMsZ+8qAQzPCAB34wynaMBy4SVoA20V5ZqJIw==";
        String data ="BnGOvdqb9aY+t44WVjDmhxzdOBkRziHwmKgUltDFz6H+91YNUqCABYPZLEJMWIofJ3zZ9ZYy8PIqThmF9YgSlNdJW73rFfXAWVxPpnS8Fjf7gIIMEZJTyfuDwmbQGEoJxq5o6AmSyqkIqwixyaop69veAyXD17BwcD+1roHILoEi3bZZxlYTr+FGwQDsBstbRJPghjP1kPd+ZrbYYx2hAbJhT+GdSTF5j29JHl6x13KKdz/XRiwvPIGW0LK83Jtq88th/FuuFLqkgHChw7WyGPWBWdI0XCejYwZUyiaYI47MfhRJl+crwiqvWFbCOOUFMqfnzXed727iXFAEH9f3Mw: =";
        try {
            String s = decryptByPrivateKey(pri,data);
            System.out.println("解密:"+s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
