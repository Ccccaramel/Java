package com.springboot.controller;

import com.springboot.bean.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j

@Controller
@ResponseBody
// 与下等价
//@RestController
public class hiController {

    @Autowired
    Car car;

    @RequestMapping("/")
    public String index(){
        return "index!";
    }

    @RequestMapping("/car")
    public Car car(){
        log.info("car!");
        return car;
    }

    @RequestMapping("/hi")
    public String hi(){
        Date date=new Date();
        log.info("hi");
        return "hi!springBoot!"+date.toString();
    }

//    @RequestMapping(value = "/hello",method = RequestMethod.GET)  // 指定请求方式
    @GetMapping("/hello")
    public String hello(){
        Date date=new Date();
        log.info("hello");
        return "hello!springBoot!"+date.toString();
    }

    // 转发
    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request){
        request.setAttribute("msg","success!");
        request.setAttribute("code",200);
        return "forward:/success";
    }
    @ResponseBody
    @GetMapping("/success")
    public Map success(
            @RequestAttribute("msg") String msg,
            @RequestAttribute("code") Integer code,
            HttpServletRequest request){
        String msgStr = (String)request.getAttribute("msg");
        Map<String,Object> map = new HashMap<>();
        map.put("code",code);
        map.put("msg",msg);
        map.put("msgStr",msgStr);
        return map;
    }

    @RequestMapping("/forward")  // 请求转发
    public void forward(HttpServletRequest request, HttpServletResponse response){
        log.info("forward");
        try {
            request.getRequestDispatcher("/hi").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/include")  // 包含
    public void include(HttpServletRequest request, HttpServletResponse response){
        log.info("include");
        try {
            request.getRequestDispatcher("/hi").include(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
