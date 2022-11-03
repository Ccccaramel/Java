package com.ding.hyld;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.vo.GadgetVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class HyldApplicationTests {

    @Autowired
    private StringEncryptor stringEncryptor;

    @Test
    public void encryptTest(){
        // 加密 -基础类型
        String userName = stringEncryptor.encrypt("123");
        System.out.println("加密："+userName);

        // 解密 -基础类型
        String decUserName = stringEncryptor.decrypt(userName);
        System.out.println("解密："+decUserName);

        // 加密 -对象
        UpdateLogInfo updateLogInfo = new UpdateLogInfo();
        updateLogInfo.setId(1);
        updateLogInfo.setCreateTime(LocalDateTime.now());
        updateLogInfo.setText("333");
        updateLogInfo.setCreateTimeStr("2020-02-20");
        updateLogInfo.setNote("4444");
        updateLogInfo.setCreateTime(LocalDateTime.now());
        String updateLogInfoStrEn = stringEncryptor.encrypt(updateLogInfo.toString());
        System.out.println("加密："+updateLogInfoStrEn);

        // 解密 -对象
        String updateLogInfoStrDe = stringEncryptor.decrypt(updateLogInfoStrEn);
        System.out.println(updateLogInfoStrDe);
        ObjectMapper objectMapper = new ObjectMapper();
        UpdateLogInfo newUpdateLogInfo =objectMapper.convertValue(JSONObject.parse(updateLogInfoStrDe),UpdateLogInfo.class);
        System.out.println("解密："+newUpdateLogInfo.toString());
    }

    @Test
    public void encryptConfig(){
        // mysql > username
        String userNameEnc = stringEncryptor.encrypt("root");
        System.out.println("username 加密："+userNameEnc);
        String userNameDec = stringEncryptor.decrypt(userNameEnc);
        System.out.println("username 解密："+userNameDec);

        // mysql > password
        String passwordEnc = stringEncryptor.encrypt("root");
        System.out.println("password 加密："+passwordEnc);
        String passwordDec = stringEncryptor.decrypt(passwordEnc);
        System.out.println("password 解密："+passwordDec);
    }

    @Test
    void contextLoads() {
    }

}
