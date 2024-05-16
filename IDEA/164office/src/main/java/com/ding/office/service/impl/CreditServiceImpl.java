package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.Credit;
import com.ding.office.info.CreditInfo;
import com.ding.office.info.SearchTeamMemberScoreboardInfo;
import com.ding.office.mapper.CreditMapper;
import com.ding.office.service.CreditService;
import com.ding.office.vo.CreditVo;
import com.ding.office.vo.Page;
import com.ding.office.vo.TeamMemberCreditVo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CreditServiceImpl extends ServiceImpl<CreditMapper, Credit> implements CreditService {
    @Override
    public List<CreditVo> getTeamMemberScoreboard(SearchTeamMemberScoreboardInfo searchTeamMemberScoreboardInfo) {
        return baseMapper.getTeamMemberScoreboard(searchTeamMemberScoreboardInfo);
    }

    @Override
    public List<CreditInfo> searchCreditBy(Page page, CreditVo creditVo) {
        return baseMapper.searchCreditBy(page,creditVo);
    }

    @Override
    public void saveTeamMemberCredit(TeamMemberCreditVo teamMemberCreditVo) {
        baseMapper.saveTeamMemberCredit(teamMemberCreditVo);
    }

    @Override
    public void batchCreditAddSave(List<TeamMemberCreditVo> allValidTeamMemberList) {
        for(TeamMemberCreditVo teamMemberCreditVo : allValidTeamMemberList){
            baseMapper.add(teamMemberCreditVo);
        }
    }

    @Override
    public void singleCreditAddSave(TeamMemberCreditVo teamMemberCreditVo) {
        baseMapper.add(teamMemberCreditVo);
    }

    @Override
    public List<LocalDateTime> getSettlementTimeList(Integer uwtId) {
        return baseMapper.getSettlementTimeList(uwtId);
    }

    @Override
    public List<CreditInfo> getTeamData(Integer uwtId, Integer teamCompetitionType) {
        return baseMapper.getTeamData(uwtId,teamCompetitionType);
    }

    @Override
    public Integer searchCreditOfPageBy(CreditVo creditVo) {
        return baseMapper.searchCreditOfPageBy(creditVo);
    }
}
