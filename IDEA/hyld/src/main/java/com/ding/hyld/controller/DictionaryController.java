package com.ding.hyld.controller;

import com.ding.hyld.info.DictionaryInfo;
import com.ding.hyld.service.DictionaryService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("findLeaveType")
    public R findLeaveType(){
        return R.success(dictionaryService.findByType("teamMemberStatus", List.of(0))); // 排除"正常队员"项
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

    @GetMapping("getTeamMemberStatusType")
    public R getTeamMemberStatusType(){
        return R.success(dictionaryService.findByType("teamMemberStatus", null));
    }

    @GetMapping("getTeamStatusType")
    public R getTeamStatusType(){
        return R.success(dictionaryService.findByType("teamStatus", null));
    }


    @GetMapping("/getTeamType")
    public R getTeamType(){
        return R.success(dictionaryService.findByType("teamType", null));
    }

    @GetMapping("/getMenuNoteType")
    public R getMenuNoteType(){
        return R.success(dictionaryService.findByType("menuNoteType", null)); // 排除"根节点"
    }

    @GetMapping("/getUserType")
    public R getUserType(){
        return R.success(dictionaryService.findByType("userType", null));
    }

    @GetMapping("/getUserStatus")
    public R getUserStatus(){
        return R.success(dictionaryService.findByType("userStatus", null));
    }

    @GetMapping("/getplayerPositionType")
    public R getplayerPositionType(){
        return R.success(dictionaryService.findByType("teamMemberPositionType", null));
    }

    @GetMapping("/getPlayerType")
    public R getPlayerType(){
        return R.success(dictionaryService.findByType("PlayerType", null));
    }

    @GetMapping("/getUserRelationPlayerCheckStatus")
    public R getUserRelationPlayerCheckStatus(){
        return R.success(dictionaryService.findByType("userRelationPlayerCheckStatus", null));
    }

    @GetMapping("/getCheckStatus")
    public R getCheckStatus(){
        return R.success(dictionaryService.findByType("checkStatus", null));
    }

    @GetMapping("/getRelationStatus")
    public R getRelationStatus(){
        return R.success(dictionaryService.findByType("relationStatus", null));
    }

    @GetMapping("searchDictionary")
    public R searchDictionary(Page page, DictionaryInfo dictionaryInfo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",dictionaryService.searchDictionary(page, dictionaryInfo));
        if(!Objects.equals(page.getSize(),null)){
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

    @PreAuthorize("hasAuthority('test')")
    @GetMapping("/deleteDictionary")
    public R deleteDictionary(Integer dictionaryId){
        if(dictionaryId==null){
            return R.fail("字典删除失败!");
        }
        dictionaryService.delete(dictionaryId);
        return R.success("字典删除成功!");
    }
}
