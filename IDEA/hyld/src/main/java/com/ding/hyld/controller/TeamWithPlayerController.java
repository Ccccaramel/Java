package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.entity.Player;
import com.ding.hyld.entity.TeamWithPlayer;
import com.ding.hyld.info.ChangeTeamMemberStatusInfo;
import com.ding.hyld.info.CreditImportInfo;
import com.ding.hyld.service.PlayerService;
import com.ding.hyld.service.TeamWithPlayerService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamMemberInfo;
import com.ding.hyld.vo.TeamMemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/teamWithPlayer")
public class TeamWithPlayerController {
    @Autowired
    TeamWithPlayerService teamWithPlayerService;

    @Autowired
    PlayerService playerService;

    @PostMapping("/changeTeamMemberStatus")
    public R changeTeamMemberStatus(@RequestBody ChangeTeamMemberStatusInfo changeTeamMemberStatusInfo){
        teamWithPlayerService.changeTeamMemberStatus(changeTeamMemberStatusInfo);
        return R.success();
    }

    @PostMapping("/addNewTeamMember")
    public R addNewTeamMember(@RequestBody TeamMemberInfo teamMemberInfo){
        // 检查该玩家是否已经被战队关联
        TeamWithPlayer teamWithPlayer=teamWithPlayerService.findTeamMember(teamMemberInfo.getScid());
        if(teamWithPlayer != null){
            if(Objects.equals(teamWithPlayer.getTeamId(), teamMemberInfo.getTeamId())){
                return R.fail("该玩家已在战队中不可重复添加!请重新输入!");
            }else{
                return R.fail("该玩家已被其他战队绑定中!请加群(475765701)联系管理员!");
            }
        }

        Player player = new Player();
        player.setScid(teamMemberInfo.getScid());
        player.setName(teamMemberInfo.getName());
        player=playerService.addPlayer(player);
        teamMemberInfo.setPlayerId(player.getId());
        teamWithPlayerService.addNewTeamMember(teamMemberInfo);
        return R.success("新队员添加成功!");
    }

    @GetMapping("/getAllValidTeamMember")
    public R getAllValidTeamMember(CreditImportInfo creditImportInfo) {
        return R.success(teamWithPlayerService.getAllValidTeamMember(creditImportInfo, DictionaryCode.TEAM_MEMBER_STATUS_1));
    }

    @GetMapping("/searchTeamMember")
    public R searchTeamMember(Page page,TeamMemberVo teamMemberVo) {
        HashMap<String,Object> result=new HashMap<>();
//        teamMemberVo.setTeamId(1); // teamScid = "YYUYC"
        result.put("data",teamWithPlayerService.searchTeamMember(page,teamMemberVo));
        if(page!=null){
            result.put("totalPage",Math.ceil(teamWithPlayerService.searchTeamMember(null,teamMemberVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }
}
