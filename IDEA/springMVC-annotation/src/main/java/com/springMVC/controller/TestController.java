package com.springMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
//    @RequestMapping("/")
//    public String testIndex(){
//        return "index";
//    }
@RequestMapping("/testException")
public String testException(){
    System.out.println(1/0);
    return "hi";
}
}
