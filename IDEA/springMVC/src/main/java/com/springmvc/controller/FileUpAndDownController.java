package com.springmvc.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Controller
public class FileUpAndDownController {

    @RequestMapping("/testDownFile")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException{
        ServletContext servletContext=session.getServletContext(); // 获取 ServletContext 对象
        String realPath=servletContext.getRealPath("/static/img/元胞1.png"); // 获取服务器中文件的真实路径
        InputStream is=new FileInputStream(realPath); // 创建输入流
        byte[] bytes=new byte[is.available()]; // 创建字节数组
        is.read(bytes); // 将流读到字节数组中
        MultiValueMap<String,String> headers=new HttpHeaders(); // 创建 HttpHeader 对象设置响应头信息
        headers.add("Content-Disposition","attachment;filename=元胞1.jpg"); // 设置下载方式以及下载文件的名字
        HttpStatus statusCode=HttpStatus.OK; // 设置响应状态码
        ResponseEntity<byte[]> responseEntity=new ResponseEntity<byte[]>(bytes,headers,statusCode); // 创建 ResponseEntity 对象
        is.close(); // 关闭输入流
        return responseEntity;
    }

    @RequestMapping("/testUpFile")
    public String testUpFile(MultipartFile photo,HttpSession session) throws IOException {
        String fileName=photo.getOriginalFilename();
        String suffixName=fileName.substring(fileName.lastIndexOf(".")); // 获取文件后缀名
        String uuid= UUID.randomUUID().toString();
        fileName=uuid+suffixName;
        ServletContext servletContext=session.getServletContext();
        String photoPath=servletContext.getRealPath("photo");
        File file=new File(photoPath);
        if(!file.exists()){
            file.mkdir();
        }
        String finalPath=photoPath+File.separator+fileName;
        photo.transferTo(new File(finalPath));
        return "target";
    }

    @RequestMapping("/testInterceptor")
    public String testInterceptor(){
        return "success";
    }
}
