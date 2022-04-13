package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.admin.dao.ItemBackDao;
import com.muke.onlineedu.admin.entity.ItemBack;
import com.muke.onlineedu.admin.entity.TestOptions;
import com.muke.onlineedu.admin.entity.TestQuestion;
import com.muke.onlineedu.admin.service.ItemBackService;
import com.muke.onlineedu.common.info.AnswerSheet;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Service("itemBackService")
public class ItemBackServiceImpl extends ServiceImpl<ItemBackDao, ItemBack> implements ItemBackService {
    private List<TestQuestion> listTestQuestion=new ArrayList<>();
    private List<TestOptions> listTestOption=new ArrayList<>();


    @Override
    public void addItemBack(ItemBack itemBack) {  //添加新测试卷
        baseMapper.addItemBack(itemBack);
    }
    @Override
    public void updateItemBack(int courseId,String testName,int totalScore,int passLine,int testId) {  //添加新测试卷
        baseMapper.updateItemBack(courseId,testName,totalScore,passLine,testId);
    }
    @Override
    public int addTestQuestion(int questionNumber,String questionContent,int rightKey,String analysis,int score) {  //保存测试卷题目分值等
        String sql="insert into test_question(testId,questionNumber,questionContent,rightKey,analysis,score) "
                + "VALUES(?,?,?,?,?,?) "
                + "ON DUPLICATE KEY UPDATE questionContent=?,rightKey=?,analysis=?,score=?";
        int result,questionId = 0;

        return questionId;
    }

    @Override
    public void delTestQuestion(int testId, int questionNumber) {
    }

    @Override
    public void saveTestOption(int questionNumber,int optionNumber,String optionContent) {  //保存测试卷选项文本
        String sql="insert into test_options(questionId,questionOption,optionContent) VALUES(?,?,?) ON DUPLICATE KEY UPDATE questionId=?,questionOption=?,optionContent=?";
    }
    @Override
    public void delTestOption(int testId,int questionNumber) {  //删除指定测试卷的指定题目的选项
        String sql1="select questionId from test_question where testId=? and questionNumber=?";
    }
    @Override
    public int generateKeys(PreparedStatement prep) {  //获取刚插入数据的自增 id
        ResultSet result;
        return -1;
    }
    @Override
    public List<ItemBack> getTeacherTestMessage(int startPage, int pageSize, int teacherId){  //获取教师自己的所有测试卷基本信息
        return baseMapper.getTeacherTestMessage(startPage,pageSize,teacherId);
    }
    @Override
    public List<ItemBack> getPartTestMessage(int startPage,int pageSize,String key){  //获取教师自己的所有测试卷基本信息
        return baseMapper.getPartTestMessage((startPage),pageSize,key);
    }
    @Override
    public ItemBack findBy(int testId){  //获取教师自己指定测试卷基本信息
        return baseMapper.findBy(testId);
    }
    @Override
    public List<ItemBack> getAppointTestInformation(int courseId){  //获取指定课程的测试卷基本信息
        return baseMapper.getAppointTestInformation(courseId);
    }

    @Override
    public int getTeacherTestRow(int teacherId){  //获取教师自己的测试卷总数
        return baseMapper.getTeacherAllTest(teacherId).size();
    }
    @Override
    public int getAllTestRow(String key){  //获取所有测试卷总数
        return baseMapper.getAllTest(key).size();
    }
    @Override
    public void updateTestState(int testId,int sign) {  //更新测试卷状态
        baseMapper.updateTestState(testId,sign);
    }
    @Override
    public String getTestStateStr(int testId) {  //获取指定测试卷的状态(中文含义)
        String sql="select accountClass from item_back i join account_status a on i.testStatus=a.accountNumber where testId=?";
        PreparedStatement prep;
        ResultSet result;
        String state=null;
        return state;
    }
    @Override
    public String getTestName(int testId) {  //获取指定测试卷名字
        String sql="select testName from item_back where testId=?";
        PreparedStatement prep;
        ResultSet result;
        String testName=null;
        return testName;
    }
    @Override
    public boolean checkTeachersTest(int testId,int teacherId) {  //检查指定测试卷是否为指定教师的测试卷
        ItemBack itemBack = baseMapper.getTeachersTest(testId, teacherId);
        return itemBack != null;
    }

    private void getTestOption(int questionId,int questionNumber) {  //获取指定题目的各个选项信息
        String sql="select questionOption,optionContent from test_options where questionId=?";
        PreparedStatement prep;
        ResultSet result;
    }


    @Override
    public List<TestOptions> getTestOption(){
        return this.listTestOption;
    }
    @Override
    public int getQuestionId(int testId,int questionNumber) {  //根据测试卷id和题目序号获取题目id
        String sql="select questionId from test_question where testId=? and questionNumber=?";
        return 1;
    }
}
