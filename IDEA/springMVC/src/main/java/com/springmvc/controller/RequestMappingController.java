package com.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Req")
public class RequestMappingController {

    @RequestMapping("/test1")
    public String test(){
        return "test1";
    }

}