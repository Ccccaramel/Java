package com.ding.office.controller;

import com.ding.office.constant.DictionaryCode;
import com.ding.office.info.DictionaryInfo;
import com.ding.office.service.DictionaryService;
import com.ding.office.utils.R;
import com.ding.office.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("findLeaveType")
    public R findLeaveType(){
        List<Integer> excludeTypeValueList = new ArrayList<>();
        excludeTypeValueList.add(DictionaryCode.TEAM_STATUS_1_value);
//        return R.success(dictionaryService.findByType("teamMemberStatus", Arrays.asList(DictionaryCode.TEAM_STATUS_1_value))); // 排除"正常队员"项,本地有效，但云服务器上就报错(猜测与jdk版本有关)
        return R.success(dictionaryService.findByType("teamMemberStatus", excludeTypeValueList)); // 排除"正常队员"项
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

    @GetMapping("/getTeamMemberPositionTypeExcludeViceCaptain")
    public R getTeamMemberPositionTypeExcludeViceCaptain(){
        List<Integer> e = new ArrayList<>();
        e.add(DictionaryCode.PLAYER_POSITION_2_value);
        return R.success(dictionaryService.findByType("teamMemberPositionType", e));
    }

    @GetMapping("/getGameRoleRarity")
    public R getGameRoleRarity(){
        return R.success(dictionaryService.findByType("gameRoleRarity", null));
    }

    @GetMapping("/getGameRolePosition")
    public R getGameRolePosition(){
        return R.success(dictionaryService.findByType("gameRolePosition", null));
    }

    @GetMapping("/getGearRarity")
    public R getGearRarity(){
        return R.success(dictionaryService.findByType("gearRarity", null));
    }

    @GetMapping("/getBlogPower")
    public R getBlogPower(){
        return R.success(dictionaryService.findByType("blogPower", null));
    }

    @GetMapping("/getBlogType")
    public R getBlogType(){
        return R.success(dictionaryService.findByType("blogType", null));
    }

    @GetMapping("/getBillType")
    public R getBillType(){
        return R.success(dictionaryService.findByType("billType", null));
    }

    @GetMapping("/getBillWeight")
    public R getBillWeight(){
        return R.success(dictionaryService.findByType("billWeight", null));
    }

    @GetMapping("/getCurrencyType")
    public R getCurrencyType(){
        return R.success(dictionaryService.findByType("currencyType", null));
    }

    @GetMapping("/getBillStatus")
    public R getBillStatus(){
        return R.success(dictionaryService.findByType("billStatus", null));
    }

    @GetMapping("/getMusicStatus")
    public R getMusicStatus(){
        return R.success(dictionaryService.findByType("musicStatus", null));
    }

    @GetMapping("/getMusicLyricStatus")
    public R getMusicLyricStatus(){
        return R.success(dictionaryService.findByType("musicLyricStatus", null));
    }


    @GetMapping("searchDictionary")
    public R searchDictionary(Page page, DictionaryInfo dictionaryInfo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",dictionaryService.searchDictionary(page, dictionaryInfo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(dictionaryService.searchDictionaryOfPage(dictionaryInfo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PreAuthorize("hasAnyAuthority('dictionaryManage_add','dictionaryManage_update')")
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

    @PreAuthorize("hasAuthority('dictionaryManage_delete')")
    @GetMapping("/deleteDictionary")
    public R deleteDictionary(Integer dictionaryId){
        if(dictionaryId==null){
            return R.fail("字典删除失败!");
        }
        dictionaryService.delete(dictionaryId);
        return R.success("字典删除成功!");
    }
}
