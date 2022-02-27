package com.springmvc.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class hiController { // 类名前新增了一个图标,表示该类交给 AOC 容器管理

    // @RequestMapping("/") 与下等价
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping("/target1")
    public String toTarget(){
        return "target";
    }
}