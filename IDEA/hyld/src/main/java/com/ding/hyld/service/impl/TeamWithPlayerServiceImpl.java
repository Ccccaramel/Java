package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.TeamWithPlayer;
import com.ding.hyld.info.ChangeTeamMemberStatusInfo;
import com.ding.hyld.info.CreditImportInfo;
import com.ding.hyld.mapper.TeamWithPlayerMapper;
import com.ding.hyld.service.TeamWithPlayerService;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamMemberCreditVo;
import com.ding.hyld.vo.TeamMemberInfo;
import com.ding.hyld.vo.TeamMemberVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamWithPlayerServiceImpl extends ServiceImpl<TeamWithPlayerMapper, TeamWithPlayer> implements TeamWithPlayerService {
    @Override
    public List<TeamMemberVo> searchTeamMember(Page page, TeamMemberVo teamMemberVo) {
        return baseMapper.searchTeamMember(page,teamMemberVo);
    }

    @Override
    public void changeTeamMemberStatus(ChangeTeamMemberStatusInfo changeTeamMemberStatusInfo) {
        baseMapper.changeTeamMemberStatus(changeTeamMemberStatusInfo);
    }

    @Override
    public void addNewTeamMember(TeamMemberInfo teamMemberInfo) {
        baseMapper.addNewTeamMember(teamMemberInfo);
    }

    @Override
    public TeamWithPlayer findTeamMember(String playerScid) {
        return baseMapper.findTeamMember(playerScid);
    }

    @Override
    public List<TeamMemberCreditVo> getAllValidTeamMember(CreditImportInfo creditImportInfo, Integer teamMemberStatusId) {
        return baseMapper.getAllValidTeamMember(creditImportInfo,teamMemberStatusId);
    }
}
