package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.TestQuestion;
import com.muke.onlineedu.admin.entity.UserResults;

import java.util.List;

public interface UserResultsService extends IService<UserResults> {
    void saveAnswerSheet(String answerSheetId, int userId, int problemId, String userKey, int score);

    List<TestQuestion> getTestQuestionAndAnswer(String answerSheetId, List<TestQuestion> testQuestionList);
}
