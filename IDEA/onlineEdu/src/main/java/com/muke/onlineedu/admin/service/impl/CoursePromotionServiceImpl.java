package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.admin.dao.CoursePromotionDao;
import com.muke.onlineedu.admin.entity.CoursePromotion;
import com.muke.onlineedu.admin.service.CoursePromotionService;
import com.muke.onlineedu.common.tool.CommonConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("coursePromotionService")
public class CoursePromotionServiceImpl extends ServiceImpl<CoursePromotionDao, CoursePromotion> implements CoursePromotionService {

    @Override
    public int getAllCoursePromotionRow() {  //获取首页推荐的课程总数
        return baseMapper.getAll().size();
    }
    @Override
    public List<CoursePromotion> getPartCoursePromotionMessage(int startPage, int pageSize){  //返回指定页的首页推广课程信息
        return baseMapper.getPartCoursePromotionMessage(startPage,pageSize);
    }

    @Override
    public boolean courseExist(int courseId) {  //检查指定课程是否已被推广
        return baseMapper.findBy(courseId)!=null;
    }
    @Override
    public void addCoursePromotion(int courseId) {  //添加课程推广
        baseMapper.addCoursePromotion(courseId);
    }
    @Override
    public void delCoursePromotion(int courseId) {  //删除课程推广
        baseMapper.delCoursePromotion(courseId);
    }

    @Override
    public List<CoursePromotion> getAllCoursePromotionMessage(String url) {
        List<CoursePromotion> coursePromotionList = baseMapper.getAllCoursePromotionMessage();
        for(CoursePromotion coursePromotion:coursePromotionList){
            String imgAddress= url+coursePromotion.getCourse().getCourseImgName();
            coursePromotion.getCourse().setImageURL(imgAddress);
        }
        return coursePromotionList;
    }
}
