package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.TeamWithPlayer;
import com.ding.hyld.info.TeamMemberCreditInfo;
import com.ding.hyld.info.TeamMemberInfo;
import com.ding.hyld.mapper.TeamWithPlayerMapper;
import com.ding.hyld.service.TeamWithPlayerService;
import com.ding.hyld.vo.ChangeTeamMemberStatusVo;
import com.ding.hyld.vo.CreditImportVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamMemberVo;
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
}
