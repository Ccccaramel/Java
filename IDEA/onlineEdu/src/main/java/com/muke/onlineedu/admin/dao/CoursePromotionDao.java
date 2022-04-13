package com.muke.onlineedu.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.CoursePromotion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CoursePromotionDao extends BaseMapper<CoursePromotion> {
    List<CoursePromotion> getAll();
    List<CoursePromotion> getPartCoursePromotionMessage(int startPage, int pageSize);
    CoursePromotion findBy(int courseId);
    void addCoursePromotion(int courseId);
    void delCoursePromotion(int courseId);
    List<CoursePromotion> getAllCoursePromotionMessage();
}
