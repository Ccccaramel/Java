package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Team;
import com.ding.hyld.info.TeamInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamVo;

import java.util.List;

public interface TeamService extends IService<Team> {
    Team findBy(Team team);

    Team findById(Integer id);

    Team findByScid(String scid);

    void add(Team newTeam);

    void update(TeamVo teamVo);

    List<TeamInfo> searchTeamInfo(Page page, TeamVo teamVo);
}
