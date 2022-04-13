package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.Teacher;

import java.util.List;

public interface TeacherService extends IService<Teacher> {
    void updateTeacherAccountStatus(int sign,int teacherId);
    boolean registered(String teacherName,String teachersSchool,String teacherIDcard,
                    String teacherTel,String userEmail,Integer teacherQualification,
                    String userPassword,String teachersSchoolEmail);
    Teacher emailLogin(String account,String password);
    Teacher telLogin(String account,String password);
    Teacher idLogin(Integer account,String password);
    int getTeacherTableRow();
    List<Teacher> getPartTeacher(String key, int startPage, int pageSize);
    Teacher getTeacherMessage(int teacherId);
    void updateTeacherMessage(int teacherId,String teacherEmail,String teachersSchool,String teacherTel,String teachersSchoolEmail);
    boolean checkNewPassword(int id,String newPassword);
    void updatePassword(int id,String oldPassword,String newPassword);
}
