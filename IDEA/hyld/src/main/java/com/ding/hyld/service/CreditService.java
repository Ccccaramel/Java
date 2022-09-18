package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Credit;
import com.ding.hyld.info.SearchTeamMemberScoreboardInfo;
import com.ding.hyld.vo.CreditVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamMemberCreditVo;

import java.time.LocalDateTime;
import java.util.List;

public interface CreditService extends IService<Credit> {
    List<CreditVo> getTeamMemberScoreboard(SearchTeamMemberScoreboardInfo searchTeamMemberScoreboardInfo);

    List<TeamMemberCreditVo> searchCreditBy(Page page, CreditVo creditVo);

    void saveTeamMemberCredit(Credit credit);

    void batchCreditAddSave(List<TeamMemberCreditVo> allValidTeamMemberList);

    void singleCreditAddSave(TeamMemberCreditVo teamMemberCreditVo);

    List<LocalDateTime> getSettlementTimeList(Integer teamId);

    List<CreditVo> getTeamData(Integer teamId,Integer teamCompetitionType);
}
