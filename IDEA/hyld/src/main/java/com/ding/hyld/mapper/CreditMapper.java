package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Credit;
import com.ding.hyld.info.CreditInfo;
import com.ding.hyld.info.SearchTeamMemberScoreboardInfo;
import com.ding.hyld.info.TeamMemberCreditInfo;
import com.ding.hyld.vo.CreditVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamMemberCreditVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CreditMapper extends BaseMapper<Credit> {
    List<CreditVo> getTeamMemberScoreboard(@Param("searchTeamMemberScoreboardInfo") SearchTeamMemberScoreboardInfo searchTeamMemberScoreboardInfo);

    List<CreditInfo> searchCreditBy(@Param("page") Page page, @Param("creditVo") CreditVo creditVo);

    void saveTeamMemberCredit(@Param("teamMemberCreditVo") TeamMemberCreditVo teamMemberCreditVo);

    void add(@Param("teamMemberCreditVo") TeamMemberCreditVo teamMemberCreditVo);

    List<LocalDateTime> getSettlementTimeList(Integer uwtId);

    List<CreditInfo> getTeamData(Integer uwtId, Integer teamCompetitionType);

    Integer searchCreditOfPageBy(@Param("creditVo") CreditVo creditVo);
}
