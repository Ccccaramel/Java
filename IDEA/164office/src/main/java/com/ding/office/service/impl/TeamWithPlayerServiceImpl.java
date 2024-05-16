package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.TeamWithPlayer;
import com.ding.office.info.TeamMemberCreditInfo;
import com.ding.office.info.TeamMemberInfo;
import com.ding.office.mapper.TeamWithPlayerMapper;
import com.ding.office.service.TeamWithPlayerService;
import com.ding.office.vo.ChangeTeamMemberStatusVo;
import com.ding.office.vo.CreditImportVo;
import com.ding.office.vo.Page;
import com.ding.office.vo.TeamMemberVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamWithPlayerServiceImpl extends ServiceImpl<TeamWithPlayerMapper, TeamWithPlayer> implements TeamWithPlayerService {
    @Override
    public List<TeamMemberInfo> searchTeamMember(Page page, TeamMemberVo teamMemberVo) {
        return baseMapper.searchTeamMember(page,teamMemberVo);
    }

    @Override
    public void changeTeamMemberStatus(ChangeTeamMemberStatusVo changeTeamMemberStatusVo) {
        baseMapper.changeTeamMemberStatus(changeTeamMemberStatusVo);
    }

    @Override
    public void addNewTeamMember(TeamMemberVo teamMemberVo) {
        baseMapper.addNewTeamMember(teamMemberVo);
    }

    @Override
    public TeamWithPlayer findTeamMember(TeamMemberVo teamMemberVo) {
        return baseMapper.findTeamMember(teamMemberVo);
    }

    @Override
    public List<TeamMemberCreditInfo> getAllValidTeamMember(CreditImportVo creditImportVo, Integer teamMemberStatusId) {
        return baseMapper.getAllValidTeamMember(creditImportVo,teamMemberStatusId);
    }

    @Override
    public Integer searchTeamMemberOfPage(TeamMemberVo teamMemberVo) {
        return baseMapper.searchTeamMemberOfPage(teamMemberVo);
    }
}
