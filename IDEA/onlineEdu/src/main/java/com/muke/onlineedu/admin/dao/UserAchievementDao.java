package com.muke.onlineedu.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.muke.onlineedu.admin.entity.UserAchievement;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserAchievementDao extends BaseMapper<UserAchievement> {
    void saveGrades(String answerSheetId, int userId, int courseId, int testId, int usertotalScore, int totalScore, int pass);

    List<UserAchievement> findByUserId(int userId);

    List<UserAchievement> getPartUserAchievement(int userId, int startPage, int pageSize);
}
