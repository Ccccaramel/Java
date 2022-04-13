package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.Course;

import java.util.List;


public interface CourseService extends IService<Course> {
    void updateCourseStatus(int sign,int id);
    String getCourseStatusStr(int id);
    void setAsDefault(int id);
    boolean checkTeachersCourse(int teacherId,int courseId);

    void changeCourse(String courseName, String courseImg, int courseType, int courseId);

    void addCourse(Course course);

    void saveCourseAbstract(int i, String courseIntroduce);

    void delCourseAbstract(int i, int courseId);

    int getCourseTableRow(int teacherId);

    List<Course> getCourseMessage(int startPage, int pageSize, int teacherId);

    List<Course> getCourseMessage(int startPage, int pageSize, String key);

    Course findBy(int courseId);

    List<Course> getAllCourse();

    List<Course> getTeacherAllCourse(int teacherId);

    boolean checkCourseExist(int courseId);

    List<Course> getPopularCourses(String url,int amount);

    List<Course>  getNewCourses(String url,int amount);

    List<Course> getITCourses(String url,int conurseType, int amount);

    int getSourchCourseRow(String key, int startPage, int pageSize);

    List<Course> sourchCourse(String url,String key, int startPage, int pageSize);

    void heatsUp(int courseId);

    Course getAppointCourseMessage(String url,int courseId);
}
