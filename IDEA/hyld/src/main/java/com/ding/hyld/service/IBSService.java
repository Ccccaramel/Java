package com.ding.hyld.service;

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
@FeignClient(url = "https://apis.map.qq.com/ws/location/v1",name = "ibsAPI")
public interface IBSService {

    @GetMapping("/ip")
    JSONObject ip(@RequestParam("key") String key,@RequestParam("ip")String ip);
}
