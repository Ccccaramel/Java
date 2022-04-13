package com.muke.onlineedu.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.TestQuestion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestQuestionDao extends BaseMapper<TestQuestion> {
    void addTestQuestion(int testId,int questionNumber, String questionContent, int rightKey, String analysis, int score);
    void delTestQuestion(int testId,int questionNumber);
    TestQuestion findBy(int testId, int questionNumber);
    List<TestQuestion> getTestQuestion(int testId);
    List<TestQuestion> findAfterBy(int testId, int questionNumber);
    TestQuestion revise(int problemId, String userKey);
    List<TestQuestion> getTestQuestionInfo(int testId);
}
