package com.muke.onlineedu.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherDao extends BaseMapper<Teacher> {
    void registered(String teacherName,String teachersSchool,String teacherIDcard,
                    String teacherTel,String userEmail,Integer teacherQualification,
                    String userPassword,String teachersSchoolEmail);
    Teacher emailLogin(String account,String password);
    Teacher telLogin(String account,String password);
    Teacher idLogin(Integer account,String password);
    List<Teacher> getTeacherTableRow();
    List<Teacher> getPartTeacher(String key, int startPage, int pageSize);
    void updateTeacherAccountStatus(int sign,int teacherId);
    Teacher getTeacherMessage(int teacherId);
    void updateTeacherMessage(int teacherId,String teacherEmail,String teachersSchool,String teacherTel,String teachersSchoolEmail);
    Teacher checkNewPassword(int id,String newPassword);
    void updatePassword(int id,String oldPassword,String newPassword);
}
