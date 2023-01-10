package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.TeamWithPlayer;
import com.ding.hyld.info.TeamMemberCreditInfo;
import com.ding.hyld.info.TeamMemberInfo;
import com.ding.hyld.vo.ChangeTeamMemberStatusVo;
import com.ding.hyld.vo.CreditImportVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamMemberVo;

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
