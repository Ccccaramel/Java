package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.UserWithTeam;
import com.ding.hyld.info.UserWithTeamInfo;
import com.ding.hyld.vo.UserWithTeamVo;
import com.ding.hyld.vo.Page;

import java.util.List;

public interface UserWithTeamService extends IService<UserWithTeam> {
    List<UserWithTeamInfo> searchTeam(Page page,UserWithTeamVo userWithTeamVo);

    List<UserWithTeamInfo> findBy(UserWithTeamVo userWithTeamVo);

    void add(UserWithTeam userWithTeam);

    void relieveTeam(UserWithTeamVo userWithTeamVo);

    void saveCheckInfo(Integer relationId, String controllerPreparePageNewName, String teamMainPageNewName, Integer checkStatus);

    void teamExamineCheck(UserWithTeamVo userWithTeamVo);

    void addViceCaptain(UserWithTeamVo userWithTeamVo);
}
