package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.CourseAbstract;

import java.util.List;

public interface CourseAbstractService extends IService<CourseAbstract> {
    void saveCourseAbstract(int i, String courseIntroduce,int courseId);
    void delCourseAbstract(int i, int courseId);
    List<CourseAbstract> findBy(int courseId);
}
