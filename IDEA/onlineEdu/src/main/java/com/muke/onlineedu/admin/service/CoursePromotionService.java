package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.Course;
import com.muke.onlineedu.admin.entity.CoursePromotion;
import java.util.List;

public interface CoursePromotionService extends IService<CoursePromotion> {
    int getAllCoursePromotionRow();
    List<CoursePromotion> getPartCoursePromotionMessage(int startPage, int pageSize);
    boolean courseExist(int courseId);
    void addCoursePromotion(int courseId);
    void delCoursePromotion(int courseId);
    List<CoursePromotion> getAllCoursePromotionMessage(String url);
}
