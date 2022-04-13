package com.muke.onlineedu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.muke.onlineedu.common.entity.AccountStatus;
import com.muke.onlineedu.common.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@TableName("curriculum_resource")
public class CourseResource implements Serializable {
    private int resourceId;
    private String resourceName;
    private int courseId;
    private String linkAdd;
    private int resourceAccount;
    private int teacherId;
    private int userType;

    private UserType userClass;
    private AccountStatus accountStatus;
    private Teacher teacher;
    private Student student;
    private Course course;

    private String linkURL;

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getLinkAdd() {
        return linkAdd;
    }

    public void setLinkAdd(String linkAdd) {
        this.linkAdd = linkAdd;
    }

    public int getResourceAccount() {
        return resourceAccount;
    }

    public void setResourceAccount(int resourceAccount) {
        this.resourceAccount = resourceAccount;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public UserType getUserClass() {
        return userClass;
    }

    public void setUserClass(UserType userClass) {
        this.userClass = userClass;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }
}
