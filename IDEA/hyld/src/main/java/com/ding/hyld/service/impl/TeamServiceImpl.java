package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Team;
import com.ding.hyld.info.TeamInfo;
import com.ding.hyld.mapper.TeamMapper;
import com.ding.hyld.service.TeamService;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService {

    @Override
    public Team findBy(Team team) {
        return baseMapper.findBy(team);
    }

    @Override
    public Team findById(Integer id) {
        return baseMapper.findById(id);
    }

    @Override
    public Team findByScid(String scid) {
        return baseMapper.findByScid(scid);
    }

    @Override
    public void add(Team newTeam) {
        baseMapper.add(newTeam);
    }

    @Override
    public void update(TeamVo teamVo) {
        baseMapper.update(teamVo);
    }

    @Override
    public List<TeamInfo> searchTeamInfo(Page page, TeamVo teamVo) {
        return baseMapper.searchTeamInfo(page, teamVo);
    }
}