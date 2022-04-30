package com.springboot.controller;

import com.springboot.bean.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

//与下等价
//@Controller
//@ResponseBody
@Slf4j
@RestController
public class hiController {

    @Autowired
    Car car;

    @RequestMapping("/")
    public String index(){
        return "index!";
    }

    @RequestMapping("/car1")
    public Car car(){
        System.out.println(car.toString());
        log.info("@Slf4j");
        return car;
    }

    @RequestMapping("/hi")
    public String hi(){
        Date date=new Date();
        return "hi!springBoot!"+date.toString();
    }
}
