package com.muke.onlineedu.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentDao extends BaseMapper<Student> {
    void updateStudentAccountStatus(int sign,int id);
    String getStudentAccountStr(int id);
    boolean registered(String userEmail,String userPassword);
    List<Student> emailLogin(String account, String password);
    List<Student> telLogin(String account,String password);
    List<Student> idLogin(Integer account,String password);
    Student getUserMessage(int userId);
    void changeUserInformation(String userName,String userEmail,String userTel,String userSex,String userBirth,int userId);
    void changePassword(int userId, String newPassword,String oldPassword);
    List<Student> getStudentTableRow();
    List<Student> getPartStudent(String key,int startPage,int pageSize);
    void updatePassword(int id,String oldPassword,String newPassword);
    Student checkNewPassword(int id,String newPassword);
}
