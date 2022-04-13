package com.example.test01.controller;

import com.example.test01.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {
    @Autowired
    Person person;

    @RequestMapping("/person")
    public Person person(){
        return person;
    }
}
