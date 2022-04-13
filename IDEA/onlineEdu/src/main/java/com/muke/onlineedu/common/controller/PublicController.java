package com.muke.onlineedu.common.controller;

import com.google.gson.Gson;
import com.muke.onlineedu.common.entity.CourseType;
import com.muke.onlineedu.common.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PublicController {
    @Autowired
    CourseTypeService courseTypeService;

    @ResponseBody
    @RequestMapping("/gmExit")
    public void gmExit(HttpSession session){
        ServletContext servletContext = session.getServletContext();  // 使用 resuest 对象的 getSession() 获取 session ,若 session 不存在则创建一个
        servletContext.removeAttribute("userJson");
        System.out.println("application1>>"+servletContext.getAttribute("application1"));
        System.out.println("userJson>>"+servletContext.getAttribute("userJson"));
    }

    @ResponseBody
    @RequestMapping("/getAllAccounts")
    public String getAllAccounts(){
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        List<CourseType> courseTypeList=courseTypeService.getAllCourseType();
        data.put("courseTypeList", courseTypeList);
        return gson.toJson(data);
    }
}
