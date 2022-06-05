package com.muke.onlineedu.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.Course;
import com.muke.onlineedu.common.entity.CourseType;

import java.util.List;

public interface CourseTypeService extends IService<CourseType> {
    void addNewCourseType(String courseType);
    List<CourseType> getCourseType(int startPage,int pageSize);
    List<CourseType> getAllCourseType();
    void delCourseType(int id);
    String getTypeName(int typeValue);
    List<CourseType> homeGetCourseType(int amount);
}
