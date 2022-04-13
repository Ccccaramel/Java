package com.muke.onlineedu.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.UserResults;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserResultsDao extends BaseMapper<UserResults> {
    void saveAnswerSheet(String answerSheetId, int userId, int problemId, String userKey, int score);
    String findUserKeyBy(String answerSheetId,int questionId);
}
