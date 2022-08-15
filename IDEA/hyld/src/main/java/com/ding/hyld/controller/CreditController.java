package com.ding.hyld.controller;

import com.ding.hyld.entity.Credit;
import com.ding.hyld.info.SearchTeamMemberScoreboardInfo;
import com.ding.hyld.info.TeamMemberCreditInfo;
import com.ding.hyld.service.CreditService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.TimeUtils;
import com.ding.hyld.vo.CreditVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamMemberCreditVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/credit")
public class CreditController {
    @Autowired
    CreditService creditService;

    @GetMapping("/getTeamMemberScoreboard")
    public R getTeamMemberScoreboard(SearchTeamMemberScoreboardInfo searchTeamMemberScoreboardInfo){
        return R.success(creditService.getTeamMemberScoreboard(searchTeamMemberScoreboardInfo));
    }

    /**
     * 获取队员积分明细
     * @return
     */
    @GetMapping("/searchTeamMemberCredit")
    public R searchTeamMemberCredit(Page page, CreditVo creditVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",creditService.searchTeamMemberCredit(page,creditVo));
        if(page!=null){
            result.put("totalPage",Math.ceil(creditService.searchTeamMemberCredit(null,creditVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/saveTeamMemberCreditInfo")  // 修改队员积分信息
    public R saveTeamMemberCreditInfo(@RequestBody TeamMemberCreditInfo teamMemberCreditInfo){
        Credit credit=new Credit();
        credit.setId(teamMemberCreditInfo.getCreditId());
        credit.setCredit(teamMemberCreditInfo.getCredit());
        credit.setCreditType(teamMemberCreditInfo.getCreditType().getId());
        credit.setSettlementTime(teamMemberCreditInfo.getSettlementTime());
        credit.setNote(teamMemberCreditInfo.getNote());
        creditService.saveTeamMemberCredit(credit);
        return R.success();
    }

    @PostMapping("/batchCreditAddSave")  // 批量保存队员积分
    public R batchCreditAddSave(@RequestBody List<TeamMemberCreditVo> allValidTeamMemberList){
        creditService.batchCreditAddSave(allValidTeamMemberList);
        return R.success();
    }

    @PostMapping("/singleCreditAddSave")  // 批量保存队员积分
    public R singleCreditAddSave(@RequestBody TeamMemberCreditVo teamMemberCreditVo){
        creditService.singleCreditAddSave(teamMemberCreditVo);
        return R.success();
    }


    @GetMapping("/getTeamMemberAllCreditRecord")  // 获取队员积分信息
    public R getTeamMemberAllCreditRecord(CreditVo creditVo){
        return R.success(creditService.searchTeamMemberCredit(null,creditVo));
    }

    @GetMapping("/getSettlementTimeList")  // 获取结算时间集
    public R getSettlementTimeList(Integer teamId){
        List<TeamMemberCreditVo> teamMemberCreditVoList=new ArrayList<>();
        for(LocalDateTime settlementTime:creditService.getSettlementTimeList(teamId)){
            TeamMemberCreditVo teamMemberCreditVo = new TeamMemberCreditVo();
            teamMemberCreditVo.setSettlementTime(settlementTime);
            teamMemberCreditVoList.add(teamMemberCreditVo);
        }
        return R.success(teamMemberCreditVoList);
    }
}
