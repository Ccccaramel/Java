package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Team;
import com.ding.hyld.entity.UserWithTeam;
import com.ding.hyld.info.SearchTeamInfo;
import com.ding.hyld.info.UserWithTeamInfo;
import com.ding.hyld.mapper.UserWithTeamMapper;
import com.ding.hyld.service.UserWithTeamService;
import com.ding.hyld.vo.Page;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserWithTeamServiceImpl extends ServiceImpl<UserWithTeamMapper, UserWithTeam> implements UserWithTeamService {
    @Override
    public List<Team> searchTeam(Page page, SearchTeamInfo searchTeamInfo) {
        return baseMapper.searchTeam(page,searchTeamInfo);
    }

    @Override
    public List<UserWithTeam> findBy(Team newTeam) {
        return baseMapper.findBy(newTeam);
    }

    @Override
    public List<UserWithTeam> findByTeamId(Integer teamId) {
        return baseMapper.findByTeamId(teamId);
    }

    @Override
    public void add(UserWithTeam userWithTeam) {
        baseMapper.add(userWithTeam);
    }

    @Override
    public void removeTeam(Integer userId, Integer teamId) {
        baseMapper.removeTeam(userId,teamId);
    }

    @Override
    public UserWithTeam getBy(UserWithTeamInfo userWithTeamInfo) {
        return baseMapper.getBy(userWithTeamInfo);
    }

    @Override
    public void update(UserWithTeam userWithTeam) {
        baseMapper.update(userWithTeam);
    }
}
