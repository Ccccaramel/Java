package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.TestQuestion;

import java.util.List;

public interface TestQuestionService extends IService<TestQuestion> {
    void addTestQuestion(int testId,int questionNumber, String questionContent, int rightKey, String analysis, int score);

    void delTestQuestion(int testId, int questionNumber);

    Integer findBy(int testId, int questionNumber);

    List<Integer> findAfterBy(int testId, int questionNumber);

    List<TestQuestion> getTestQuestion(int testId);

    int revise(int problemId, String userKey);

    List<TestQuestion> getTestQuestionInfo(int testId);
}
