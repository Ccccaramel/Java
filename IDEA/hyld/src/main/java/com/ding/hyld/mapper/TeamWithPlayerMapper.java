package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.TeamWithPlayer;
import com.ding.hyld.info.ChangeTeamMemberStatusInfo;
import com.ding.hyld.info.CreditImportInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamMemberCreditVo;
import com.ding.hyld.vo.TeamMemberInfo;
import com.ding.hyld.vo.TeamMemberVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamWithPlayerMapper extends BaseMapper<TeamWithPlayer> {
    List<TeamMemberVo> searchTeamMember(@Param("page")Page page, @Param("teamMemberVo")TeamMemberVo teamMemberVo);

    void changeTeamMemberStatus(@Param("changeTeamMemberStatusInfo")ChangeTeamMemberStatusInfo changeTeamMemberStatusInfo);

    void addNewTeamMember(@Param("teamMemberInfo")TeamMemberInfo teamMemberInfo);

    TeamWithPlayer findTeamMember(String playerScid);

    List<TeamMemberCreditVo> getAllValidTeamMember(@Param("creditImportInfo") CreditImportInfo creditImportInfo, Integer teamMemberStatusId);
}
