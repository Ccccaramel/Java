package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.admin.dao.TeacherDao;
import com.muke.onlineedu.admin.entity.Teacher;
import com.muke.onlineedu.admin.service.TeacherService;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service("teacherService")
public class TeacherServiceImpl extends ServiceImpl<TeacherDao, Teacher> implements TeacherService {
    @Override
    public void updateTeacherAccountStatus(int sign,int teacherId){
        baseMapper.updateTeacherAccountStatus(sign,teacherId);
    }

    @Override
    public boolean registered(String teacherName,String teachersSchool,String teacherIDcard,
                    String teacherTel,String userEmail,Integer teacherQualification,
                    String userPassword,String teachersSchoolEmail){
        baseMapper.registered(teacherName,teachersSchool,teacherIDcard,teacherTel,userEmail,teacherQualification,userPassword,teachersSchoolEmail);
        return true;
    }

    @Override
    public Teacher emailLogin(String account,String password){
        return baseMapper.emailLogin(account,password);
    }

    @Override
    public Teacher telLogin(String account,String password){
        return baseMapper.telLogin(account, password);
    }

    @Override
    public Teacher idLogin(Integer account,String password){
        return baseMapper.idLogin(account, password);
    }

    @Override
    public int getTeacherTableRow(){
        List<Teacher> teacherList = baseMapper.getTeacherTableRow();
        return teacherList.size();
    }

    @Override
    public List<Teacher> getPartTeacher(String key, int startPage, int pageSize){
        return baseMapper.getPartTeacher(key,startPage,pageSize);
    }

    @Override
    public Teacher getTeacherMessage(int teacherId){
        return baseMapper.getTeacherMessage(teacherId);
    }

    @Override
    public void updateTeacherMessage(int teacherId,String teacherEmail,String teachersSchool,String teacherTel,String teachersSchoolEmail){
        baseMapper.updateTeacherMessage(teacherId, teacherEmail, teachersSchool, teacherTel, teachersSchoolEmail);
    }

    @Override
    public boolean checkNewPassword(int id,String newPassword){
        Teacher teacher = baseMapper.checkNewPassword(id, newPassword);
        return teacher==null?true:false;
    }

    @Override
    public void updatePassword(int id,String oldPassword,String newPassword){
        baseMapper.updatePassword(id,oldPassword,newPassword);
    }
}
