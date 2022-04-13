package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.muke.onlineedu.admin.dao.CourseResourceDao;
import com.muke.onlineedu.admin.entity.CourseResource;
import com.muke.onlineedu.admin.service.CourseResourceService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("courseResourceService")
public class CourseResourceServiceImpl extends ServiceImpl<CourseResourceDao, CourseResource> implements CourseResourceService {
    @Override
    public void addCourseResource(String resourceName,int courseId,String linkAdd,int userId,int userType){
        baseMapper.addCourseResource( resourceName, courseId, linkAdd, userId, userType);
    }
    @Override
    public List<CourseResource> getCourseResource(int userId,int userType,int startPage,int pageSize){
        return baseMapper.getCourseResource(userId,userType,startPage,pageSize);
    }
    @Override
    public List<CourseResource> getAppointCourseResource(int courseId,int userType){
        return baseMapper.getAppointCourseResource(courseId,userType);
    }

    @Override
    public List<CourseResource> getAllCourseResource(int startPage,int pageSize,String key){  //获取所有课程资源的信息
        return baseMapper.getPartCourseResource(startPage,pageSize,key);
    }
    @Override
    public List<CourseResource> searchCourseResource(int startPage,int pageSize,String key){  //获取所有课程资源的信息
        return baseMapper.searchPartCourseResource(startPage,pageSize,key);
    }
    @Override
    public int searchCourseResourceRow(String key){  //获取所有课程资源的信息
        return baseMapper.searchCourseResource(key).size();
    }
    @Override
    public int getUserCourseResourceRow(int userId,int userType){  //获取用户资源总数
        return baseMapper.getUserAllCourseResource(userId,userType).size();
    }
    @Override
    public int getUserCourseResourceRow(String key){  //获取所有资源总数
        return baseMapper.getAllCourseResource(key).size();
    }

    @Override
    public void updateUserCourseResource(int resourceAccount, int resourceId){  //更改用户资源状态
        baseMapper.updateUserCourseResource(resourceAccount,resourceId);
    }
    @Override
    public String getUserCourseResourceresourceAccountStr(int resourceId){  //更改用户资源状态(中文)
        String sql="select resourceAccount from curriculum_resource "
                + "where resourceId=?";
        return "";
    }

    @Override
    public CourseResource findBy(int resourceId) {
        return baseMapper.findBy(resourceId);
    }

    @Override
    public void courseResourceEntry(int id, boolean teacher, Map<String, String> data) {
        int userType=0;
        if(teacher) {
            userType=1;
        }
        for(int i=1;;i++) {  //资源
            String linkAdd=data.get(data.get("resource-"+i+"-label-hidden")); // 文件上传到服务器会重命名
            String resourceName=data.get("resource-"+i+"-label-hidden"); // 文件原始名称
            int courseId=Integer.parseInt(data.get("courseId"));
            if(linkAdd!=null&&linkAdd.length()>0) {
                addCourseResource(resourceName, courseId, linkAdd, id, userType);
            }else {
                System.out.println("不存在第"+i+"个资源");
                break;
            }
        }
    }
}
