package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.admin.dao.TestQuestionDao;
import com.muke.onlineedu.admin.entity.TestQuestion;
import com.muke.onlineedu.admin.service.TestQuestionService;
import com.muke.onlineedu.admin.service.UserResultsService;
import com.muke.onlineedu.common.info.AnswerSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("testQuestionService")
public class TestQuestionServiceImpl extends ServiceImpl<TestQuestionDao, TestQuestion> implements TestQuestionService {
    @Autowired
    UserResultsService userResultsService;
    
    @Override
    public void addTestQuestion(int testId,int questionNumber, String questionContent, int rightKey, String analysis, int score) {
        baseMapper.addTestQuestion(testId,questionNumber,questionContent,rightKey,analysis,score);
    }
    @Override
    public void delTestQuestion(int testId,int questionNumber) {  //删除指定测试卷的指定编号之后的题目
        baseMapper.delTestQuestion(testId,questionNumber);
    }

    @Override
    public Integer findBy(int testId, int questionNumber) {
        TestQuestion testQuestion=baseMapper.findBy(testId, questionNumber);
        return testQuestion.getQuestionId();
    }

    @Override
    public List<Integer> findAfterBy(int testId, int questionNumber) {
        List<Integer> questionIdList=new ArrayList<>();
        List<TestQuestion> testQuestionList = baseMapper.findAfterBy(testId, questionNumber);
        for(TestQuestion testQuestion:testQuestionList){
            questionIdList.add(testQuestion.getQuestionId());
        }
        return questionIdList;
    }

    @Override
    public List<TestQuestion> getTestQuestion(int testId) {
        return baseMapper.getTestQuestion(testId);
    }

    @Override
    public int revise(int problemId, String userKey) {
        TestQuestion testQuestion = baseMapper.revise(problemId, userKey);
        return testQuestion==null?0:testQuestion.getScore();
    }

    @Override
    public List<TestQuestion> getTestQuestionInfo(int testId) {
        return baseMapper.getTestQuestionInfo(testId);
    }
}
