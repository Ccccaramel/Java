package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.ItemBack;
import com.muke.onlineedu.admin.entity.TestOptions;
import com.muke.onlineedu.admin.entity.TestQuestion;

import java.sql.PreparedStatement;
import java.util.List;

public interface ItemBackService extends IService<ItemBack> {
    void addItemBack(ItemBack itemBack);
    void updateItemBack(int courseId,String testName,int totalScore,int passLine,int testId);
    int addTestQuestion(int questionNumber,String questionContent,int rightKey,String analysis,int score);
    void delTestQuestion(int testId,int questionNumber);
    void saveTestOption(int questionNumber,int optionNumber,String optionContent);
    void delTestOption(int testId,int questionNumber);
    int generateKeys(PreparedStatement prep);
    List<ItemBack> getTeacherTestMessage(int startPage, int pageSize, int teacherId);
    List<ItemBack> getPartTestMessage(int startPage,int pageSize,String key);
    ItemBack findBy(int testId);
    List<ItemBack> getAppointTestInformation(int testId);
    int getTeacherTestRow(int teacherId);
    int getAllTestRow(String key);
    void updateTestState(int id,int sign);
    String getTestStateStr(int id);
    String getTestName(int testId);
    boolean checkTeachersTest(int testId,int teacherId);
    List<TestOptions> getTestOption();
    int getQuestionId(int testId,int questionNumber);

}

