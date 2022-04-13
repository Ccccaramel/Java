package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.admin.dao.CourseAbstractDao;
import com.muke.onlineedu.admin.entity.CourseAbstract;
import com.muke.onlineedu.admin.service.CourseAbstractService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseAbstractService")
public class CourseAbstractServiceImpl extends ServiceImpl<CourseAbstractDao, CourseAbstract> implements CourseAbstractService {
    @Override
    public void saveCourseAbstract(int i, String courseIntroduce,int courseId) {
        baseMapper.saveCourseAbstract(i,courseIntroduce,courseId);
    }

    @Override
    public void delCourseAbstract(int i, int courseId) {
        baseMapper.delCourseAbstract(i,courseId);
    }

    @Override
    public List<CourseAbstract> findBy(int courseId) {
        return baseMapper.findBy(courseId);
    }
}
