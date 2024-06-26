package com.ding.office.controller;

import com.ding.office.constant.DictionaryCode;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.info.CreditInfo;
import com.ding.office.info.SearchTeamMemberScoreboardInfo;
import com.ding.office.info.TeamMemberCreditInfo;
import com.ding.office.service.CreditService;
import com.ding.office.utils.R;
import com.ding.office.vo.CreditVo;
import com.ding.office.vo.Page;
import com.ding.office.vo.TeamMemberCreditVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/credit")
public class CreditController extends BaseController {
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
            result.put("totalPage",Math.ceil(creditService.searchCreditOfPageBy(creditVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PreAuthorize("hasAuthority('credit_update')")
    @PostMapping("/saveTeamMemberCreditInfo")  // 修改队员积分信息
    public R saveTeamMemberCreditInfo(@RequestBody TeamMemberCreditVo teamMemberCreditVo){
        creditService.saveTeamMemberCredit(teamMemberCreditVo);
        return R.success("积分修改成功!");
    }

    @PreAuthorize("hasAuthority('credit_add')")
    @PostMapping("/batchCreditAddSave")  // 批量队员积分新增
    public R batchCreditAddSave(@RequestBody List<TeamMemberCreditVo> allValidTeamMemberList){
        creditService.batchCreditAddSave(allValidTeamMemberList);
        return R.success("批量积分导入成功!");
    }

    @PreAuthorize("hasAuthority('credit_add')")
    @PostMapping("/singleCreditAddSave")  // 单个队员积分新增
    public R singleCreditAddSave(@RequestBody TeamMemberCreditVo teamMemberCreditVo){
        creditService.singleCreditAddSave(teamMemberCreditVo);
        return R.success("积分导入成功!");
    }


    @GetMapping("/getTeamMemberAllCreditRecord")  // 获取队员积分信息
    public R getTeamMemberAllCreditRecord(CreditVo creditVo){
        return R.success(creditService.searchCreditBy(null,creditVo));
    }

    @GetMapping("/getSettlementTimeList")  // 获取结算时间集
    public R getSettlementTimeList(Integer uwtId){
        List<TeamMemberCreditInfo> teamMemberCreditInfoList=new ArrayList<>();
        for(LocalDateTime settlementTime:creditService.getSettlementTimeList(uwtId)){
            TeamMemberCreditInfo teamMemberCreditInfo = new TeamMemberCreditInfo();
            teamMemberCreditInfo.setSettlementTime(settlementTime);
            teamMemberCreditInfoList.add(teamMemberCreditInfo);
        }
        return R.success(teamMemberCreditInfoList);
    }

    @GetMapping("/getTeamData")  // 获取结算时间集
    public R getTeamData(Integer uwtId){
        Map<String,Map<String,List>> res = new HashMap<>();

        List<String> nameList=new ArrayList<>();
        List<Integer> creditList=new ArrayList<>();
        for(CreditInfo creditInfo:creditService.getTeamData(uwtId, DictionaryCode.TEAM_COMPETITION_TYPE_1)){
            nameList.add(creditInfo.getSettlementTimeDate());
            creditList.add(creditInfo.getTotalCredit());
        }
        Map<String,List> competitionMap = new HashMap<>();
        competitionMap.put("nameList",nameList);
        competitionMap.put("creditList",creditList);
        res.put("competition",competitionMap);

        // map只是引用
        // nameList.clear();
        // creditList.clear();
        List<String> nameList2=new ArrayList<>();
        List<Integer> creditList2=new ArrayList<>();
        for(CreditInfo creditInfo:creditService.getTeamData(uwtId, DictionaryCode.TEAM_COMPETITION_TYPE_2)){
            nameList2.add(creditInfo.getSettlementTimeDate());
            creditList2.add(creditInfo.getTotalCredit());
        }
        Map<String,List> taskMap = new HashMap<>();
        taskMap.put("nameList",nameList2);
        taskMap.put("creditList",creditList2);
        res.put("task",taskMap);

        return R.success(res);
    }
}
