package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.UserWithPlayer;
import com.ding.office.info.UserWithPlayerInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.UserWithPlayerVo;

import java.util.List;

public interface UserWithPlayerService extends IService<UserWithPlayer> {
    UserWithPlayer findBy(UserWithPlayerVo userWithPlayerVo);

    void add(UserWithPlayerVo userWithPlayerVo);

    List<UserWithPlayerInfo> searchRelation(UserWithPlayerVo userWithPlayerVo, Page page);

    void changeRelationStatus(Integer playerId, Integer userId, Integer relationStatus);

    void saveCheckInfo(Integer relationId, String controllerPreparePageNewName, String playerPreparePageNewName, Integer checkStatus2);

    void updateCheckInfo(UserWithPlayerVo userWithPlayerVo);

    UserWithPlayerInfo findById(Integer id);

    Integer searchRelationOfPage(UserWithPlayerVo userWithPlayerVo);
}
