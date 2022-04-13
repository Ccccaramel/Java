package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.admin.dao.UserResultsDao;
import com.muke.onlineedu.admin.entity.TestQuestion;
import com.muke.onlineedu.admin.entity.UserResults;
import com.muke.onlineedu.admin.service.UserResultsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userResultsService")
public class UserResultsServiceImpl extends ServiceImpl<UserResultsDao, UserResults> implements UserResultsService {
    @Override
    public void saveAnswerSheet(String answerSheetId, int userId, int problemId, String userKey, int score) {
        baseMapper.saveAnswerSheet(answerSheetId,userId,problemId,userKey,score);
    }

    @Override
    public List<TestQuestion> getTestQuestionAndAnswer(String answerSheetId, List<TestQuestion> testQuestionList) {
        for(TestQuestion testQuestion:testQuestionList){
            testQuestion.setUserKey(baseMapper.findUserKeyBy(answerSheetId, testQuestion.getQuestionId()));
            testQuestion.setUserIsRight(testQuestion.getRightKey().equals(testQuestion.getUserKey()));
        }
        return testQuestionList;
    }

}
