package com.ding.office.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 最初是放在浏览器端去请求的,但那样会将我的 key 直接暴露出去
 *
 * 腾讯 ibs
 *  1.根据 ip 定位
 */
@FeignClient(url = "https://apis.map.qq.com/ws/location/v1",name = "qqAPI")
public interface QQIPService {

    /**
     * 通过通讯开放平台提供的第三方平台查询 ip 的相关地理信息
     * @param key 本平台申请的 key
     * @param ip 目标 ip
     * @return ip 相关的地理信息
     */
    @GetMapping("/ip")
    JSONObject ip(@RequestParam("key") String key,@RequestParam("ip")String ip);
}
