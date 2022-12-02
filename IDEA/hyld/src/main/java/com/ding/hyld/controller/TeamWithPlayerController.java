package com.ding.hyld.controller;

import com.ding.hyld.constant.CommonCode;
import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.entity.Player;
import com.ding.hyld.entity.TeamWithPlayer;
import com.ding.hyld.info.ChangeTeamMemberStatusInfo;
import com.ding.hyld.info.PlayerInfo;
import com.ding.hyld.service.PlayerService;
import com.ding.hyld.service.TeamWithPlayerService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/teamWithPlayer")
public class TeamWithPlayerController {
    @Autowired
    private TeamWithPlayerService teamWithPlayerService;

    @Autowired
    private PlayerService playerService;

    /**
     * 踢出
     * @param changeTeamMemberStatusVo
     * @return
     */
    @PreAuthorize("hasAnyAuthority('myTeamMember_remove','teamMatchStatistics_remove')")
    @PostMapping("/changeTeamMemberStatus")
    public R changeTeamMemberStatus(@RequestBody ChangeTeamMemberStatusVo changeTeamMemberStatusVo){
        teamWithPlayerService.changeTeamMemberStatus(changeTeamMemberStatusVo);
        return R.success();
    }

    @PreAuthorize("hasAuthority('myTeamMember_add')")
    @PostMapping("/addNewTeamMember")
    public R addNewTeamMember(@RequestBody TeamMemberVo teamMemberVo){
        // 检查战队成员上限
        TeamMemberVo searchCondition = new TeamMemberVo();
        searchCondition.setUwtId(teamMemberVo.getUwtId());
        searchCondition.setTeamMemberStatusId(DictionaryCode.TEAM_MEMBER_STATUS_1);
        if(teamWithPlayerService.searchTeamMember(null,searchCondition).size()>30){
            return R.fail("战队队员超过30人!请核实队员信息");
        }

        // 检查该玩家是否已经被战队关联
        teamMemberVo.setTeamMemberStatusId(DictionaryCode.TEAM_MEMBER_STATUS_1);
        TeamWithPlayer teamWithPlayer=teamWithPlayerService.findTeamMember(teamMemberVo);
        if(teamWithPlayer != null){
            return R.fail("该玩家已在战队中不可重复添加!请重新输入!");
        }
        PlayerVo playerVo = new PlayerVo();
        playerVo.setScid(teamMemberVo.getPlayerScid());
        playerVo.setName(teamMemberVo.getPlayerName());
        playerVo.setType(teamMemberVo.getType());
        teamMemberVo.setPlayerId(playerService.addPlayer(playerVo));
        teamWithPlayerService.addNewTeamMember(teamMemberVo);
        return R.success("新队员添加成功!");
    }

    @GetMapping("/getAllValidTeamMember")
    public R getAllValidTeamMember(CreditImportVo creditImportVo) {
        return R.success(teamWithPlayerService.getAllValidTeamMember(creditImportVo, DictionaryCode.TEAM_MEMBER_STATUS_1));
    }

    @GetMapping("/searchTeamMember")
    public R searchTeamMember(Page page,TeamMemberVo teamMemberVo) {
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",teamWithPlayerService.searchTeamMember(page,teamMemberVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(teamWithPlayerService.searchTeamMember(null,teamMemberVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }
}
