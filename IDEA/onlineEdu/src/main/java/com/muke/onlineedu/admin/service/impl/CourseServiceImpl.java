package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.admin.dao.CourseDao;
import com.muke.onlineedu.admin.entity.Course;
import com.muke.onlineedu.admin.service.CourseService;
import com.muke.onlineedu.common.tool.CommonConfig;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("courseService")
public class CourseServiceImpl extends ServiceImpl<CourseDao, Course> implements CourseService {
    @Override
    public void updateCourseStatus(int sign,int id){
        baseMapper.updateCourseStatus(sign,id);
    }
    @Override
    public String getCourseStatusStr(int id){
        return "";
    }

    @Override
    public void setAsDefault(int id) {  // 删除某课程类型之前将属于该类型的课程更改为"未知"类型
        baseMapper.setAsDefault(id);
    }

    @Override
    public boolean checkTeachersCourse(int teacherId,int courseId){
        Course course=baseMapper.getCourse(teacherId,courseId);
        return course==null?false:true;
    }

    @Override
    public void changeCourse(String courseName, String courseImg, int courseType, int courseId){
        baseMapper.changeCourse(courseName,courseImg,courseType,courseId);
    }

    @Override
    public void addCourse(Course course) {
        baseMapper.addCourse(course);
    }

    @Override
    public void saveCourseAbstract(int i, String courseIntroduce) {
        baseMapper.saveCourseAbstract(i,courseIntroduce);
    }

    @Override
    public void delCourseAbstract(int i, int courseId) {
        baseMapper.delCourseAbstract(i,courseId);
    }

    @Override
    public int getCourseTableRow(int teacherId) {
        List<Course> courses = baseMapper.getTeacherAllCourse(teacherId);
        return courses.size();
    }

    @Override
    public List<Course> getCourseMessage(int startPage, int pageSize, int teacherId) {
        return baseMapper.getCourseMessage(startPage, pageSize, teacherId);
    }

    @Override
    public List<Course> getCourseMessage(int startPage, int pageSize, String key){
        return baseMapper.getAllCourseMessage(startPage,pageSize,key);
    }

    @Override
    public Course findBy(int courseId) {
        return baseMapper.findBy(courseId);
    }

    @Override
    public List<Course> getAllCourse() {
        return baseMapper.getAllCourse();
    }

    @Override
    public List<Course> getAllValidCourse(){
        return baseMapper.getAllValidCourse();
    }

    @Override
    public List<Course> getPartValidCourse(String url,int startPage, int pageSize){
//        return baseMapper.getPartValidCourse(startPage, pageSize);
        List<Course> popularCoursesList = baseMapper.getPartValidCourse(startPage, pageSize);
        for(Course course:popularCoursesList){
            String imgAddress= url+course.getCourseImgName();
            course.setImageURL(imgAddress);
        }
        return popularCoursesList;
    }

    @Override
    public List<Course> getTeacherAllCourse(int teacherId) {
        return baseMapper.getTeacherAllCourse(teacherId);
    }

    @Override
    public boolean checkCourseExist(int courseId) {
        return baseMapper.findBy(courseId)!=null;
    }

    @Override
    public List<Course> getPopularCourses(String url,int amount){
        List<Course> popularCoursesList = baseMapper.getPopularCourses(amount);
        for(Course course:popularCoursesList){
            String imgAddress= url+course.getCourseImgName();
            course.setImageURL(imgAddress);
        }
        return popularCoursesList;
    }

    @Override
    public List<Course> getNewCourses(String url,int amount) {
        List<Course> newCoursesList = baseMapper.getNewCourses(amount);
        for(Course course:newCoursesList){
            String imgAddress= url+course.getCourseImgName();
            course.setImageURL(imgAddress);
        }
        return newCoursesList;
    }

    @Override
    public List<Course> getITCourses(String url,int conurseType, int amount) {
        List<Course> itCoursesList = baseMapper.getAppointCourses(conurseType, amount);
        for(Course course:itCoursesList){
            String imgAddress= url+course.getCourseImgName();
            course.setImageURL(imgAddress);
        }
        return itCoursesList;
    }

    @Override
    public int getSearchCourseRow(String key) {
        return baseMapper.getSearchCourseRow(key).size();
    }

    @Override
    public List<Course> searchCourse(String url, String key, int startPage, int pageSize) {
        List<Course> coursesList = baseMapper.getPartCourseMessage(key,startPage,pageSize);
        for(Course course:coursesList){
            String imgAddress= url+course.getCourseImgName();
            course.setImageURL(imgAddress);
        }
        return coursesList;
    }

    @Override
    public void heatsUp(int courseId) {
        baseMapper.heatsUp(courseId);
    }

    @Override
    public Course getAppointCourseMessage(String url,int courseId) {
        Course course= baseMapper.getAppointCourseMessage(courseId);
        course.setImageURL(url+course.getCourseImgName());
        return course;
    }

    @Override
    public List<Course> findAllByAdoptCourseType(int courseClass){
        return baseMapper.findAllByAdoptCourseType(courseClass);
    }
    @Override
    public List<Course> findByAdoptCourseType(String url,int courseClass,int startPage,int pageSize){
//        return baseMapper.findByAdoptCourseType(courseClass,startPage,pageSize);
        List<Course> coursesList =baseMapper.findByAdoptCourseType(courseClass,startPage,pageSize);
        for(Course course:coursesList){
            String imgAddress= url+course.getCourseImgName();
            course.setImageURL(imgAddress);
        }
        return coursesList;
    }
}
