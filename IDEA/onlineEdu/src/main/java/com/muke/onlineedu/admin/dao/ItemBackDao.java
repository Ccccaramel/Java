package com.muke.onlineedu.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.ItemBack;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ItemBackDao extends BaseMapper<ItemBack> {
    void updateItemBack(int courseId,String testName,int totalScore,int passLine,int testId);
    void addItemBack(@Param("itemBack") ItemBack itemBack);
    List<ItemBack> getTeacherAllTest(int teacherId);
    List<ItemBack> getTeacherTestMessage(int startPage, int pageSize, int teacherId);
    void updateTestState(int testId,int sign);
    ItemBack getTeachersTest(int testId,int teacherId);
    ItemBack findBy(int testId);
    List<ItemBack> getPartTestMessage(int startPage,int pageSize,String key);
    List<ItemBack> getAllTest(String key);
    List<ItemBack> getAppointTestInformation(int courseId);
}
