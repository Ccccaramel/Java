package com.muke.onlineedu.admin.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.CourseAbstract;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseAbstractDao extends BaseMapper<CourseAbstract> {
    void saveCourseAbstract(int i, String courseIntroduce,int courseId);
    void delCourseAbstract(int i, int courseId);
    List<CourseAbstract> findBy(int courseId);
    String findFirstBy(int courseId);
}
