package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.Team;
import com.ding.office.info.TeamInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.TeamVo;

import java.util.List;

public interface TeamService extends IService<Team> {
    TeamInfo findById(Integer id);

    TeamInfo findByScid(String scid);

    void add(TeamVo teamVo);

    void update(TeamVo teamVo);

    List<TeamInfo> searchTeamInfo(Page page, TeamVo teamVo);

    Integer searchTeamInfoOfPage(TeamVo teamVo);
}
