package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Team;
import com.ding.hyld.entity.UserWithTeam;
import com.ding.hyld.info.SearchTeamInfo;
import com.ding.hyld.info.UserWithTeamInfo;
import com.ding.hyld.vo.Page;

import java.util.List;

public interface UserWithTeamService extends IService<UserWithTeam> {
    List<Team> searchTeam(Page page, SearchTeamInfo searchTeamInfo);

    List<UserWithTeam> findBy(Team newTeam);

    List<UserWithTeam> findByTeamId(Integer teamId);

    void add(UserWithTeam userWithTeam);

    void removeTeam(Integer userId, Integer teamId);

    UserWithTeam getBy(UserWithTeamInfo userWithTeamInfo);

    void update(UserWithTeam userWithTeam);
}
