package com.muke.onlineedu.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.CourseResource;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface CourseResourceDao extends BaseMapper<CourseResource> {
    void addCourseResource(String resourceName,int courseId,String linkAdd,int userId,int userType);
    List<CourseResource> getUserAllCourseResource(int userId, int userType);
    List<CourseResource> getAllCourseResource(String key);
    List<CourseResource> getCourseResource(int userId,int userType,int startPage,int pageSize);
    List<CourseResource> getPartCourseResource(int startPage,int pageSize,String key);
    List<CourseResource> searchCourseResource(String key);
    List<CourseResource> searchPartCourseResource(int startPage,int pageSize,String key);
    CourseResource findBy(int resourceId);
    List<CourseResource> getAppointCourseResource(int courseId,int userType);
    void updateUserCourseResource(int resourceAccount, int resourceId);
}
