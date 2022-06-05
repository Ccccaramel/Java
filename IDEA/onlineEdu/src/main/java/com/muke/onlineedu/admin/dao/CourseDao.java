package com.muke.onlineedu.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseDao extends BaseMapper<Course> {
    void updateCourseStatus(int sign,int id);
    String getCourseStatusStr(int id);
    void setAsDefault(int id);
    Course getCourse(int teacherId,int courseId);
    void changeCourse(String courseName, String courseImg, int courseType, int courseId);
    void addCourse(@Param("course") Course course);
    void saveCourseAbstract(int i, String courseIntroduce);
    void delCourseAbstract(int i, int courseId);
    List<Course> getAllCourse(int teacherId);
    List<Course>  getCourseMessage(int startPage, int pageSize, int teacherId);
    List<Course> getAllCourseMessage(int startPage, int pageSize, String key);
    Course findBy(int courseId);
    List<Course> getAllCourse();
    List<Course> getAllValidCourse();
    List<Course> getPartValidCourse(int startPage, int pageSize);
    List<Course> getTeacherAllCourse(int teacherId);
    Course getCourseInfo(int courseId);
    List<Course> getPopularCourses(int amount);
    List<Course>  getNewCourses(int amount);

    List<Course> getAppointCourses(int conurseType, int amount);
    List<Course> getPartCourseMessage(String key, int startPage, int pageSize);
    void heatsUp(int courseId);
    Course getAppointCourseMessage(int courseId);
    List<Course> findAllByAdoptCourseType(int courseClass);
    List<Course> findByAdoptCourseType(int courseClass, int startPage, int pageSize);
    List<Course> getSearchCourseRow(String key);
}
