package com.muke.onlineedu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.muke.onlineedu.common.entity.AccountStatus;
import com.muke.onlineedu.common.entity.CourseType;

import java.io.Serializable;
import java.util.List;

@TableName("course")
public class Course implements Serializable {

    private int courseId;
    private String courseName;
    private String courseImgName;
    private int courseClass;
    private int teacherId;
    private int courseStatus;
    private int heat;

    private CourseType courseType;
    private AccountStatus accountStatus;
    private Teacher teacher;

    private String courseFirstAbstract;
    private List<CourseAbstract> courseAbstracts;
    private List<CourseStructure> courseStructures;

    private String imageURL;

    public Course() {
    }

    public Course(String courseName, String courseImgName, int courseClass, int teacherId) {
        this.courseName = courseName;
        this.courseImgName = courseImgName;
        this.courseClass = courseClass;
        this.teacherId = teacherId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseImgName() {
        return courseImgName;
    }

    public void setCourseImgName(String courseImgName) {
        this.courseImgName = courseImgName;
    }



    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

    public int getCourseClass() {
        return courseClass;
    }

    public void setCourseClass(int courseClass) {
        this.courseClass = courseClass;
    }

    public int getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(int courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCourseFirstAbstract() {
        return courseFirstAbstract;
    }

    public void setCourseFirstAbstract(String courseFirstAbstract) {
        this.courseFirstAbstract = courseFirstAbstract;
    }

    public List<CourseAbstract> getCourseAbstracts() {
        return courseAbstracts;
    }

    public void setCourseAbstracts(List<CourseAbstract> courseAbstracts) {
        this.courseAbstracts = courseAbstracts;
    }

    public List<CourseStructure> getCourseStructures() {
        return courseStructures;
    }

    public void setCourseStructures(List<CourseStructure> courseStructures) {
        this.courseStructures = courseStructures;
    }
}
