package com.ding.hyld.controller;

import com.ding.hyld.entity.User;
import com.ding.hyld.service.UserService;
import com.ding.hyld.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/test")
    public R test(){
        return new R();
    }

    @PostMapping("/currentUser")
    public R currentUser(@RequestHeader("Authorization") String token){
        userService.findUserByToken(token);
        return new R(token);
    }
}
