package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.CourseResource;

import java.util.List;
import java.util.Map;

public interface CourseResourceService extends IService<CourseResource> {
    void addCourseResource(String resourceName,int courseId,String linkAdd,int userId,int userType);
    List<CourseResource> getCourseResource(int userId, int userType, int startPage, int pageSize);
    List<CourseResource> getAppointCourseResource(int courseId,int userType);
    List<CourseResource> getAllCourseResource(int startPage,int pageSize,String key);
    List<CourseResource> searchCourseResource(int startPage,int pageSize,String key);
    int searchCourseResourceRow(String key);
    int getUserCourseResourceRow(int userId,int userType);
    int getUserCourseResourceRow(String key);
    void updateUserCourseResource(int resourceAccount, int resourceId);
    String getUserCourseResourceresourceAccountStr(int resourceId);
    CourseResource findBy(int resourceId);
    void courseResourceEntry(int id, boolean teacher, Map<String,String> data);
}
