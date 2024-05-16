package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.Team;
import com.ding.office.info.TeamInfo;
import com.ding.office.mapper.TeamMapper;
import com.ding.office.service.TeamService;
import com.ding.office.vo.Page;
import com.ding.office.vo.TeamVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService {

    @Override
    public TeamInfo findById(Integer id) {
        return baseMapper.findById(id);
    }

    @Override
    public TeamInfo findByScid(String scid) {
        return baseMapper.findByScid(scid);
    }

    @Override
    public void add(TeamVo teamVo) {
        baseMapper.add(teamVo);
    }

    @Override
    public void update(TeamVo teamVo) {
        baseMapper.update(teamVo);
    }

    @Override
    public List<TeamInfo> searchTeamInfo(Page page, TeamVo teamVo) {
        return baseMapper.searchTeamInfo(page, teamVo);
    }

    @Override
    public Integer searchTeamInfoOfPage(TeamVo teamVo) {
        return baseMapper.searchTeamInfoOfPage(teamVo);
    }
}
