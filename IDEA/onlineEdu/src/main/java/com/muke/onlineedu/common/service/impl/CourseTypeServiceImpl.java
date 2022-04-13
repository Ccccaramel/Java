package com.muke.onlineedu.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.common.dao.CourseTypeDao;
import com.muke.onlineedu.common.entity.CourseType;
import com.muke.onlineedu.common.service.CourseTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseTypeService")
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeDao, CourseType> implements CourseTypeService {
    @Override
    public void addNewCourseType(String courseType){  // 添加新课程类型
        baseMapper.addNewCourseType(courseType);
    }
//    @Override
//    public List<CourseType> getcourseType(){  //获取所有课程类型以及id
//        List<CourseType> courseTypeList=new ArrayList<>();
//        String sql="select typeValue,typeName from course_type";
//        return courseTypeList;
//    }

    @Override
    public List<CourseType> getCourseType(int startPage,int pageSize){  // 获取指定页数的课程类型
        return baseMapper.getCourseType(startPage,pageSize);
    }
    @Override
    public List<CourseType> getAllCourseType(){  // 获取表数据的总行数
        List<CourseType> courseTypeList = baseMapper.getAllCourseType();
        return courseTypeList;
    }

    @Override
    public void delCourseType(int id) {  // 删除指定课程类型
        baseMapper.delCourseType(id);
    }
    @Override
    public String getTypeName(int typeValue){  //获取指定课程类型(数字)的中文含义
        CourseType courseType = baseMapper.findBy(typeValue);
         return courseType.getTypeName();
    }

    @Override
    public List<CourseType> homeGetCourseType(int amount){
        return baseMapper.homeGetCourseType(amount);
    }
}
