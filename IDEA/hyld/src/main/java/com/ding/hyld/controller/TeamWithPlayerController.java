package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.entity.Player;
import com.ding.hyld.entity.TeamWithPlayer;
import com.ding.hyld.info.ChangeTeamMemberStatusInfo;
import com.ding.hyld.service.PlayerService;
import com.ding.hyld.service.TeamWithPlayerService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.ChangeTeamMemberStatusVo;
import com.ding.hyld.vo.CreditImportVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamMemberVo;
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

    @PostMapping("/changeTeamMemberStatus")
    public R changeTeamMemberStatus(@RequestBody ChangeTeamMemberStatusVo changeTeamMemberStatusVo){
        teamWithPlayerService.changeTeamMemberStatus(changeTeamMemberStatusVo);
        return R.success();
    }

    @PostMapping("/addNewTeamMember")
    public R addNewTeamMember(@RequestBody TeamMemberVo teamMemberVo){
        // 检查该玩家是否已经被战队关联
        TeamWithPlayer teamWithPlayer=teamWithPlayerService.findTeamMember(teamMemberVo.getScid());
        if(teamWithPlayer != null){
            if(Objects.equals(teamWithPlayer.getTeamId(), teamMemberVo.getTeamId())){
                return R.fail("该玩家已在战队中不可重复添加!请重新输入!");
            }else{
                return R.fail("该玩家已被其他战队绑定中!请加群(475765701)联系管理员!");
            }
        }

        Player player=playerService.addPlayer(teamMemberVo);
        teamMemberVo.setPlayerId(player.getId());
        teamWithPlayerService.addNewTeamMember(teamMemberVo);
        return R.success("新队员添加成功!");
    }

    @GetMapping("/getAllValidTeamMember")
    public R getAllValidTeamMember(CreditImportVo creditImportVo) {
        return R.success(teamWithPlayerService.getAllValidTeamMember(creditImportVo, DictionaryCode.TEAM_MEMBER_STATUS_1));
    }

    @PreAuthorize("hasAuthority('test')")
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
