package com.muke.onlineedu.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class ManageController {

    @RequestMapping("/admin")
    public ModelAndView gmIndex(){
        return new ModelAndView("/administration/GMLogin");
    }

//    @RequestMapping("/gmLogin")
//    public ModelAndView gmLogin(){
//        return new ModelAndView("/administration/ManagementSystem");
//    }
}
