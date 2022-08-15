package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.TeamWithPlayer;
import com.ding.hyld.info.ChangeTeamMemberStatusInfo;
import com.ding.hyld.info.CreditImportInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamMemberCreditVo;
import com.ding.hyld.vo.TeamMemberInfo;
import com.ding.hyld.vo.TeamMemberVo;

import java.util.List;

public interface TeamWithPlayerService extends IService<TeamWithPlayer> {

    List<TeamMemberVo> searchTeamMember(Page page, TeamMemberVo teamMemberVo);

    void changeTeamMemberStatus(ChangeTeamMemberStatusInfo changeTeamMemberStatusInfo);

    /**
     * 将玩家添加至战队中
     * @param teamMemberInfo
     */
    void addNewTeamMember(TeamMemberInfo teamMemberInfo);

    TeamWithPlayer findTeamMember(String playerScid);

    List<TeamMemberCreditVo> getAllValidTeamMember(CreditImportInfo creditImportInfo, Integer teamMemberStatusId);
}
