package com.muke.onlineedu.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@TableName("teacher_basic_information")
public class Teacher implements Serializable {
    private int teacherId;
    private String teacherName;
    private String teachersSchool;
    private String teacherIDcard;
    private String teacherTel;
    private String teacherEmail;
    private int teacherQualification;
    private String teacherOldPassword;
    private String teacherNewPassword;
    private int teacherAccountStatus;
    private String teachersSchoolEmail;

    @TableField(exist = false)
    private String accountClass;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeachersSchool() {
        return teachersSchool;
    }

    public void setTeachersSchool(String teachersSchool) {
        this.teachersSchool = teachersSchool;
    }

    public String getTeacherIDcard() {
        return teacherIDcard;
    }

    public void setTeacherIDcard(String teacherIDcard) {
        this.teacherIDcard = teacherIDcard;
    }

    public String getTeacherTel() {
        return teacherTel;
    }

    public void setTeacherTel(String teacherTel) {
        this.teacherTel = teacherTel;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public int getTeacherQualification() {
        return teacherQualification;
    }

    public void setTeacherQualification(int teacherQualification) {
        this.teacherQualification = teacherQualification;
    }

    public String getTeacherOldPassword() {
        return teacherOldPassword;
    }

    public void setTeacherOldPassword(String teacherOldPassword) {
        this.teacherOldPassword = teacherOldPassword;
    }

    public String getTeacherNewPassword() {
        return teacherNewPassword;
    }

    public void setTeacherNewPassword(String teacherNewPassword) {
        this.teacherNewPassword = teacherNewPassword;
    }

    public int getTeacherAccountStatus() {
        return teacherAccountStatus;
    }

    public void setTeacherAccountStatus(int teacherAccountStatus) {
        this.teacherAccountStatus = teacherAccountStatus;
    }

    public String getTeachersSchoolEmail() {
        return teachersSchoolEmail;
    }

    public void setTeachersSchoolEmail(String teachersSchoolEmail) {
        this.teachersSchoolEmail = teachersSchoolEmail;
    }

    public String getAccountClass() {
        return accountClass;
    }

    public void setAccountClass(String accountClass) {
        this.accountClass = accountClass;
    }
}
