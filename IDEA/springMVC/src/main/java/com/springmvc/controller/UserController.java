package com.springmvc.controller;

import com.springmvc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;

public class UserController {

    // 自动装配
    @Autowired
    private UserDao userDao;
}
