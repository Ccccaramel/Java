package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.Credit;
import com.ding.office.info.CreditInfo;
import com.ding.office.info.SearchTeamMemberScoreboardInfo;
import com.ding.office.vo.CreditVo;
import com.ding.office.vo.Page;
import com.ding.office.vo.TeamMemberCreditVo;

import java.time.LocalDateTime;
import java.util.List;

public interface CreditService extends IService<Credit> {
    List<CreditVo> getTeamMemberScoreboard(SearchTeamMemberScoreboardInfo searchTeamMemberScoreboardInfo);

    List<CreditInfo> searchCreditBy(Page page, CreditVo creditVo);

    void saveTeamMemberCredit(TeamMemberCreditVo teamMemberCreditVo);

    void batchCreditAddSave(List<TeamMemberCreditVo> allValidTeamMemberList);

    void singleCreditAddSave(TeamMemberCreditVo teamMemberCreditVo);

    List<LocalDateTime> getSettlementTimeList(Integer uwtId);

    List<CreditInfo> getTeamData(Integer uwtId, Integer teamCompetitionType);

    Integer searchCreditOfPageBy(CreditVo creditVo);
}
