package com.example.demo.controller;

import com.example.demo.config.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private Info info;

    @GetMapping("/getInfo")
    private String getInfo(){
        return info.toString();
    }
}
