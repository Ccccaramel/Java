package com.ding.hyld.controller;

import com.ding.hyld.service.LoginService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/test1")
    public String test1(){
        return "123";
    }

    @PostMapping("/login")
    public R login(@RequestBody UserVo userVo){
        return R.success(loginService.login(userVo, 1, TimeUnit.DAYS));
    }

}
