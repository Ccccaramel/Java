package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.TeamWithPlayer;
import com.ding.office.info.TeamMemberCreditInfo;
import com.ding.office.info.TeamMemberInfo;
import com.ding.office.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TeamWithPlayerMapper extends BaseMapper<TeamWithPlayer> {
    List<TeamMemberInfo> searchTeamMember(@Param("page")Page page, @Param("teamMemberVo")TeamMemberVo teamMemberVo);

    void changeTeamMemberStatus(@Param("changeTeamMemberStatusVo") ChangeTeamMemberStatusVo changeTeamMemberStatusVo);

    void addNewTeamMember(@Param("teamMemberVo")TeamMemberVo teamMemberVo);

    TeamWithPlayer findTeamMember(@Param("teamMemberVo")TeamMemberVo teamMemberVo);

    List<TeamMemberCreditInfo> getAllValidTeamMember(@Param("creditImportVo") CreditImportVo creditImportVo, Integer teamMemberStatusId);

    Integer searchTeamMemberOfPage(@Param("teamMemberVo") TeamMemberVo teamMemberVo);
}
