package com.springboot.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/ding")
public class RequestController {
    /**
     * 请求样例
     *   1.http://127.0.0.1:8081/ding/user/id/1/name/tom?age=10&hobby=ball&hobby=game
     *   2.手动在 header 中添加自定义 cookie 值
     * 当 hobby 有多个时 infoList 只保存一个
     */
    @GetMapping("/user/id/{id}/name/{name}")
    public Map<String,Object> getUser(
            // 路径变量
            @PathVariable("id") String id,  // 获取指定映射值
            @PathVariable("name") String name,
            @PathVariable Map<String,String> userMap,  // 获取所有 k-v
            // 请求头
            @RequestHeader("User-Agent")String userAgent,  // 获取指定请求头
            @RequestHeader Map<String,String> headers,  // 获取所有请求头
            // 请求参数
            @RequestParam("age") Integer age,  // 获取所有请求头
            @RequestParam("hobby") List<String> hobby,  // 获取所有请求头
            @RequestParam Map<String,String> infoList,  // 获取所有请求头
            // Cookie
            @CookieValue("uid") String uid,  // 获取指定 Cookie 的值,使用 Postman 发送请求需要在 Headers 中手动添加 k-y 键值对
            @CookieValue("uid") Cookie cookie  // 获取指定 Cookie 的所有信息
    ){
        Map<String,Object> data=new HashMap<>();
        data.put("id",id);
        data.put("name",name);
        data.put("userMap",userMap);
        data.put("userAgent",userAgent);
        data.put("headers",headers);
        data.put("age",age);
        data.put("hobby",hobby);
        data.put("infoList",infoList);
        data.put("uid",uid);
        data.put("cookie",cookie);
        return data;
    }

    @PostMapping("/save")
    public Map<String,Object> postUser(
            // 获取请求体
            @RequestBody String content // 通常获取 POST 请求的表单数据
    ){
        Map<String,Object> data=new HashMap<>();
        data.put("content",content);
        return data;
    }
}
