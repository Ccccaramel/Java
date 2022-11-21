package com.ding.hyld.controller;

import com.ding.hyld.utils.R;
import com.ding.hyld.utils.RsaUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/common")
public class CommonController {

    @GetMapping("/getPublicKey")
    public R getPublicKey(){
        Map<String,String> result = new HashMap<>();
//        String str = "{" +
//                "'id':11111111111111111111111111111111" +
//                ", 'userId':2" +
//                ", 'userName':''" +
//                ", 'ip':'127.0.0.1'" +
//                ", 'address':'china'" +
//                ", 'trueAddress':'武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉武汉'" +
//                ", 'note':'哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈'" +
//                ", 'startDate':'2020-01-01T10:10:10'" +
//                ", 'endDate':'2020-01-01T10:10:10'" +
//                ", 'time':'2020-01-01T10:10:10'" +
//                '}';
//        try {
//            String s = RsaUtils.encryptByPublicKey(str);
//            System.out.println("test加密:"+s);
//            result.put("d",str);
//            System.out.println("test解密:"+RsaUtils.decryptByPrivateKey(s));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        result.put("publicKey",RsaUtils.getRsaKeyPair().getPublicKey());
//        result.put("privateKey",RsaUtils.getRsaKeyPair().getPrivateKey());
//        System.out.println("publicKey:"+RsaUtils.getRsaKeyPair().getPublicKey());
//        System.out.println("privateKey:"+RsaUtils.getRsaKeyPair().getPrivateKey());
        return R.success(result);
    }
}
