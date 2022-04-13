package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.admin.entity.Student;
import com.muke.onlineedu.admin.service.StudentService;
import com.muke.onlineedu.admin.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {
    @Override
    public void updateStudentAccountStatus(int sign, int id){
    }
    @Override
    public String getStudentAccountStr(int id){
        return "";
    }

    @Override
    public boolean registered(String userEmail,String userPassword){
        baseMapper.registered(userEmail,userPassword);
        return true;
    }

    @Override
    public Student emailLogin(String account,String password){
        List<Student> list = baseMapper.emailLogin(account, password);
        if(list.size()==1){
            return list.get(0);
        }
        else{
            return null;
        }
    }

    @Override
    public Student telLogin(String account,String password){
        List<Student> list = baseMapper.telLogin(account,password);
        if(list.size()==1){
            return list.get(0);
        }
        else{
            return null;
        }
    }

    @Override
    public Student idLogin(Integer account,String password){
        List<Student> list = baseMapper.idLogin(account,password);
        if(list.size()==1){
            return list.get(0);
        }
        else{
            return null;
        }
    }

    @Override
    public Student getUserMessage(int userId){
        return baseMapper.getUserMessage(userId);
    }

    @Override
    public void changeUserInformation(String userName,String userEmail,String userTel,String userSex,String userBirth,int userId){
        baseMapper.changeUserInformation(userName,userEmail,userTel,userSex,userBirth,userId);
    }

    @Override
    public void changePassword(int userId, String newPassword,String oldPassword){
        baseMapper.changePassword(userId,newPassword,oldPassword);
    }

    @Override
    public int getStudentTableRow(){
        List<Student> studentList=baseMapper.getStudentTableRow();
        return studentList.size();
    }

    @Override
    public List<Student> getPartStudent(String key,int startPage,int pageSize){
        List<Student> studentList=baseMapper.getPartStudent(key,startPage,pageSize);
        return studentList;
    }

    @Override
    public boolean checkNewPassword(int id,String userNewPassword){
        Student student=baseMapper.checkNewPassword(id,userNewPassword);
        return student==null?true:false;
    }

    @Override
    public void updatePassword(int id,String oldPassword,String newPassword){
        baseMapper.updatePassword(id,oldPassword,newPassword);
    }
}
