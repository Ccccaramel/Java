package com.springboot.controller;

import com.springboot.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

//与下等价
//@Controller
//@ResponseBody
@RestController
public class hiController {

    @Autowired
    Car car;

    @RequestMapping("/car")
    public Car car(){
        return car;
    }

    @RequestMapping("/hi")
    public String hi(){
        Date date=new Date();
        return "hi!springBoot!"+date.toString();
    }
}
