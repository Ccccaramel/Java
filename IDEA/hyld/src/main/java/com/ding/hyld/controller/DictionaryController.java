package com.ding.hyld.controller;

import com.ding.hyld.info.DictionaryInfo;
import com.ding.hyld.service.DictionaryService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {
    @Autowired
    DictionaryService dictionaryService;

    @GetMapping("findLeaveType")
    public R findLeaveType(){
        return R.success(dictionaryService.findByType("teamMemberStatus", List.of(100))); // 排除"正常队员"项
    }

    @GetMapping("findJoinWayType")
    public R findJoinWayType(){
        return R.success(dictionaryService.findByType("joinWay", null));
    }

    @GetMapping("findCreditType")
    public R findCreditType(){
        return R.success(dictionaryService.findByType("creditType", null));
    }

    @GetMapping("getTeamCompetitionType")
    public R getTeamCompetitionType(){
        return R.success(dictionaryService.findByType("teamCompetitionType", null));
    }

    @GetMapping("getTeamStatusType")
    public R getTeamStatusType(){
        return R.success(dictionaryService.findByType("teamMemberStatus", null)); // 排除"正常队员"项
    }

    @GetMapping("searchDictionary")
    public R searchDictionary(Page page, DictionaryInfo dictionaryInfo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",dictionaryService.searchDictionary(page, dictionaryInfo));
        if(page!=null){
            result.put("totalPage",Math.ceil(dictionaryService.searchDictionary(null,dictionaryInfo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("saveDictionaryInfo")
    public R saveDictionaryInfo(@RequestBody DictionaryInfo dictionaryInfo){
        if(dictionaryInfo.isAdd()){
            dictionaryService.addDictionaryInfo(dictionaryInfo);
            return R.success("字典新增成功!");
        }else{
            dictionaryService.updateDictionaryInfo(dictionaryInfo);
            return R.success("字典修改成功!");
        }


    }
}
