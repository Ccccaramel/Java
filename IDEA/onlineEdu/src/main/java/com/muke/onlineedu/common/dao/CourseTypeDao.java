package com.muke.onlineedu.common.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.common.entity.CourseType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseTypeDao extends BaseMapper<CourseType> {
    void setAsDefault(int id);
    void addNewCourseType(String courseType);
    List<CourseType> getCourseType(int startPage, int pageSize);
    List<CourseType> getAllCourseType();
    void delCourseType(int id);
    CourseType findBy(int typeValue);
    List<CourseType> homeGetCourseType(int amount);
}
