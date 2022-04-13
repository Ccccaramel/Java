package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.Student;
import org.thymeleaf.model.ICDATASection;

import java.util.List;

public interface StudentService extends IService<Student> {

    void updateStudentAccountStatus(int sign, int id);

    String getStudentAccountStr(int id);

    boolean registered(String userEmail,String userPassword);

    Student emailLogin(String account,String password);

    Student telLogin(String account,String password);

    Student idLogin(Integer account,String password);

    Student getUserMessage(int userId);

    void changeUserInformation(String userName,String userEmail,String userTel,String userSex,String userBirth,int userId);

    void changePassword(int userId, String newPassword,String oldPassword);

    int getStudentTableRow();

    List<Student> getPartStudent(String key,int startPage,int pageSize);

    boolean checkNewPassword(int id,String newPassword);

    void updatePassword(int id,String oldPassword,String userNewPassword);
}
