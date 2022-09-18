package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.entity.Credit;
import com.ding.hyld.info.SearchTeamMemberScoreboardInfo;
import com.ding.hyld.info.TeamMemberCreditInfo;
import com.ding.hyld.service.CreditService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.CreditVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamMemberCreditVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/credit")
public class CreditController {
    @Autowired
    private CreditService creditService;

    @GetMapping("/getTeamMemberScoreboard")
    public R getTeamMemberScoreboard(SearchTeamMemberScoreboardInfo searchTeamMemberScoreboardInfo){
        return R.success(creditService.getTeamMemberScoreboard(searchTeamMemberScoreboardInfo));
    }

    /**
     * 获取队员积分明细
     * @return
     */
    @GetMapping("/searchCreditBy")
    public R searchCreditBy(Page page, CreditVo creditVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",creditService.searchCreditBy(page,creditVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(creditService.searchCreditBy(null,creditVo).size()*1.0/page.getSize()));
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
        return R.success(creditService.searchCreditBy(null,creditVo));
    }

    @GetMapping("/getSettlementTimeList")  // 获取结算时间集
    public R getSettlementTimeList(Integer teamId){
        List<TeamMemberCreditInfo> teamMemberCreditInfoList=new ArrayList<>();
        for(LocalDateTime settlementTime:creditService.getSettlementTimeList(teamId)){
            TeamMemberCreditInfo teamMemberCreditInfo = new TeamMemberCreditInfo();
            teamMemberCreditInfo.setSettlementTime(settlementTime);
            teamMemberCreditInfoList.add(teamMemberCreditInfo);
        }
        return R.success(teamMemberCreditInfoList);
    }

    @GetMapping("/getTeamData")  // 获取结算时间集
    public R getTeamData(Integer teamId){
        Map<String,Map<String,List>> res = new HashMap<>();

        List<String> nameList1=new ArrayList<>();
        List<Integer> creditList1=new ArrayList<>();
        for(CreditVo creditVo:creditService.getTeamData(teamId, DictionaryCode.TEAM_COMPETITION_TYPE_1)){
            nameList1.add(creditVo.getSettlementTimeDate());
            creditList1.add(creditVo.getTotalCredit());
        }
        Map<String,List> competitionMap = new HashMap<>();
        competitionMap.put("nameList",nameList1);
        competitionMap.put("creditList",creditList1);
        res.put("competition",competitionMap);

        // map只是引用
        // nameList.clear();
        // creditList.clear();
        List<String> nameList2=new ArrayList<>();
        List<Integer> creditList2=new ArrayList<>();
        for(CreditVo creditVo:creditService.getTeamData(teamId, DictionaryCode.TEAM_COMPETITION_TYPE_2)){
            nameList2.add(creditVo.getSettlementTimeDate());
            creditList2.add(creditVo.getTotalCredit());
        }
        Map<String,List> taskMap = new HashMap<>();
        taskMap.put("nameList",nameList2);
        taskMap.put("creditList",creditList2);
        res.put("task",taskMap);

        return R.success(res);
    }
}
