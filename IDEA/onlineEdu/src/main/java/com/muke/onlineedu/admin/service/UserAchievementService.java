package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.UserAchievement;

import java.util.List;
import java.util.Map;

public interface UserAchievementService extends IService<UserAchievement> {
    void saveUserTest(Map<String, String> data, int userId);

    int getAppointUserAchievementRow(int userId);

    List<UserAchievement> getPartUserAchievement(int userId, int startPage, int pageSize);
}
