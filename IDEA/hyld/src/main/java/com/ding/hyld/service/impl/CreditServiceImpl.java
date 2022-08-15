package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Credit;
import com.ding.hyld.info.SearchTeamMemberScoreboardInfo;
import com.ding.hyld.mapper.CreditMapper;
import com.ding.hyld.service.CreditService;
import com.ding.hyld.vo.CreditVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamMemberCreditVo;
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
    public List<TeamMemberCreditVo> searchTeamMemberCredit( Page page, CreditVo creditVo) {
        return baseMapper.searchTeamMemberCredit(page,creditVo);
    }

    @Override
    public void saveTeamMemberCredit(Credit credit) {
        baseMapper.saveTeamMemberCredit(credit);
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
    public List<LocalDateTime> getSettlementTimeList(Integer teamId) {
        return baseMapper.getSettlementTimeList(teamId);
    }
}
