package com.muke.onlineedu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.muke.onlineedu.common.entity.UserType;

import java.io.Serializable;
import java.util.Date;

@TableName("note")
public class Note implements Serializable {
    private int courseId;
    private int userId;
    private int userType;
    private String note;
    private Date sendingDate;

    private UserType userTypeInfo;

    private Teacher teacher;
    private Student student;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }

    public UserType getUserTypeInfo() {
        return userTypeInfo;
    }

    public void setUserTypeInfo(UserType userTypeInfo) {
        this.userTypeInfo = userTypeInfo;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
