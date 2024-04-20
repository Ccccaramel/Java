package com.ding.hyld.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ding.hyld.constant.CommonCode;
import com.ding.hyld.info.ibs.AddressInfo;
import com.ding.hyld.info.ibs.IpInfo;
import com.ding.hyld.service.QQIPService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class QQIPServiceImpl {

    @Autowired
    private QQIPService qqIPService;

    public Map<String,String> getAddress(String ips){
        Map<String,String> res = new HashMap<>();
        try{
            IpInfo ipInfo = JSONObject.toJavaObject(qqIPService.ip(CommonCode.QQ_IP_IBS_KEY,ips), IpInfo.class);
            AddressInfo addressInfo = ipInfo.getResult().getAd_info();
            res.put("address",addressInfo.getNation()+addressInfo.getProvince());
            res.put("trueAddress",addressInfo.getNation()+addressInfo.getProvince()+addressInfo.getCity()+addressInfo.getDistrict());
        }catch (Exception e){
            res.put("address","M78星云");
            res.put("trueAddress","M78星云");
            log.info("IBS请求异常");
        }
        return res;
    }
}
