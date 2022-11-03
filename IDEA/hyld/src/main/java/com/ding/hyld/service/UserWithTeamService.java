package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.UserWithTeam;
import com.ding.hyld.info.UserWithTeamInfo;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.UserWithTeamVo;
import com.ding.hyld.vo.Page;

import java.util.Collection;
import java.util.List;

public interface UserWithTeamService extends IService<UserWithTeam> {
    List<UserWithTeamInfo> searchTeam(Page page,UserWithTeamVo userWithTeamVo);

    List<UserWithTeamInfo> findBy(UserWithTeamVo userWithTeamVo);

    void add(UserWithTeamVo userWithTeamVo);

    void relieveTeam(UserWithTeamVo userWithTeamVo);

    void saveCheckInfo(Integer relationId, String controllerPreparePageNewName, String teamMainPageNewName, Integer checkStatus);

    void teamExamineCheck(UserWithTeamVo userWithTeamVo);

    void addViceCaptain(UserWithTeamVo userWithTeamVo);

    List<UserWithTeamInfo> searchValidTeamInfo(Page page, UserWithTeamVo userWithTeamVo);

    /**
     * 用户关联游戏账号,获取录入该账号且已被验证通过的战队的关联信息
     * 正常来说只有一个,但假如验证通过的战队 A 和 B 都录入了游戏账号 a
     * 那么 a 就有两个战队,这是无法避免的,所以结果可能是多个的
     * @param userWithTeamVo
     * @return
     */
    List<UserWithTeamInfo> playerFindTeamBy(UserWithTeamVo userWithTeamVo);

    R teamTransfer(UserWithTeamVo userWithTeamVo);

    UserWithTeamInfo findById(Integer id);

    void updateTeamCreditScore(UserWithTeamVo userWithTeamVo);
}
