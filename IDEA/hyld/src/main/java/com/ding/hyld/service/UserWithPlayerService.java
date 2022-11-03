package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.UserWithPlayer;
import com.ding.hyld.info.UserWithPlayerInfo;
import com.ding.hyld.info.UserWithTeamInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UserWithPlayerVo;

import java.util.List;

public interface UserWithPlayerService extends IService<UserWithPlayer> {
    UserWithPlayer findBy(UserWithPlayerVo userWithPlayerVo);

    void add(UserWithPlayerVo userWithPlayerVo);

    List<UserWithPlayerInfo> searchRelation(UserWithPlayerVo userWithPlayerVo, Page page);

    void changeRelationStatus(Integer playerId, Integer userId, Integer relationStatus);

    void saveCheckInfo(Integer relationId, String controllerPreparePageNewName, String playerPreparePageNewName, Integer checkStatus2);

    void updateCheckInfo(UserWithPlayerVo userWithPlayerVo);

    UserWithPlayerInfo findById(Integer id);
}
