package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.TeamWithPlayer;
import com.ding.office.info.TeamMemberCreditInfo;
import com.ding.office.info.TeamMemberInfo;
import com.ding.office.vo.ChangeTeamMemberStatusVo;
import com.ding.office.vo.CreditImportVo;
import com.ding.office.vo.Page;
import com.ding.office.vo.TeamMemberVo;

import java.util.List;

public interface TeamWithPlayerService extends IService<TeamWithPlayer> {

    List<TeamMemberInfo> searchTeamMember(Page page, TeamMemberVo teamMemberVo);

    void changeTeamMemberStatus(ChangeTeamMemberStatusVo changeTeamMemberStatusVo);

    /**
     * 将玩家添加至战队中
     * @param teamMemberVo
     */
    void addNewTeamMember(TeamMemberVo teamMemberVo);

    TeamWithPlayer findTeamMember(TeamMemberVo teamMemberVo);

    List<TeamMemberCreditInfo> getAllValidTeamMember(CreditImportVo creditImportVo, Integer teamMemberStatusId);

    Integer searchTeamMemberOfPage(TeamMemberVo teamMemberVo);
}
