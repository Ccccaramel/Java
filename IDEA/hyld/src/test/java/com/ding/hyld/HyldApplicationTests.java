package com.ding.hyld;

import com.alibaba.fastjson2.annotation.JSONField;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class HyldApplicationTests {

    @Autowired
    private StringEncryptor stringEncryptor;

    /**
     * 测试加密时记得解开配置文件 jasypt.encryptor.password 注解
     */
//    @Test
//    public void encryptTest(){
//        // 加密 -基础类型
//        String userName = stringEncryptor.encrypt("123");
//        System.out.println("加密："+userName);
//
//        // 解密 -基础类型
//        String decUserName = stringEncryptor.decrypt(userName);
//        System.out.println("解密："+decUserName);
//
//        // 加密 -对象
//        UpdateLogInfo updateLogInfo = new UpdateLogInfo();
//        updateLogInfo.setId(1);
//        updateLogInfo.setCreateTime(LocalDateTime.now());
//        updateLogInfo.setText("333");
//        updateLogInfo.setCreateTimeStr("2020-02-20");
//        updateLogInfo.setNote("4444");
//        updateLogInfo.setCreateTime(LocalDateTime.now());
//        String updateLogInfoStrEn = stringEncryptor.encrypt(updateLogInfo.toString());
//        System.out.println("加密："+updateLogInfoStrEn);
//
//        // 解密 -对象
//        String updateLogInfoStrDe = stringEncryptor.decrypt(updateLogInfoStrEn);
//        System.out.println(updateLogInfoStrDe);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule()); // ...,如果包含时间类型的属性的话,加上它吧
//        UpdateLogInfo newUpdateLogInfo =objectMapper.convertValue(JSONObject.parse(updateLogInfoStrDe),UpdateLogInfo.class);
//        System.out.println("解密："+newUpdateLogInfo.toString());
//    }

    /**
     * 测试加密时记得解开配置文件 jasypt.encryptor.password 注解
     * 为配置文件中的 mysql 配置信息加密
     */
//    @Test
//    public void encryptConfig(){
//        // mysql > username
//        String userNameEnc = stringEncryptor.encrypt("root");
//        System.out.println("username 加密："+userNameEnc);
//        String userNameDec = stringEncryptor.decrypt(userNameEnc);
//        System.out.println("username 解密："+userNameDec);
//
//        // mysql > password
//        String passwordEnc = stringEncryptor.encrypt("root");
//        System.out.println("password 加密："+passwordEnc);
//        String passwordDec = stringEncryptor.decrypt(passwordEnc);
//        System.out.println("password 解密："+passwordDec);
//
//        // 云 mysql > password
//        String cloudPasswordEnc = stringEncryptor.encrypt("***");
//        System.out.println("云 password 加密："+cloudPasswordEnc);
//        String cloudPasswordDec = stringEncryptor.decrypt(cloudPasswordEnc);
//        System.out.println("云 password 解密："+cloudPasswordDec);
//    }


    private class Test1 {
        @JSONField(format = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime localDateTime;

        public LocalDateTime getLocalDateTime() {
            return localDateTime;
        }

        public void setLocalDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
        }
    }

    @Test
    public void Test2(){

    }

}
