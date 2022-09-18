package com.ding.hyld.controller;

import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.entity.Player;
import com.ding.hyld.info.PlayerInfo;
import com.ding.hyld.info.SearchTeamMemberScoreboardInfo;
import com.ding.hyld.service.CreditService;
import com.ding.hyld.service.PlayerService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.CreditVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.PlayerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/player")
public class PlayerController extends BaseController {
    @Autowired
    PlayerService playerService;

    @GetMapping("/searchPlayerInfo")
    public R searchPlayerInfo(Page page, PlayerVo playerVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",playerService.searchPlayerInfo(page,playerVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(playerService.searchPlayerInfo(null,playerVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/savePlayer")
    public R savePlayer(@RequestBody PlayerVo playerVo){
        if(playerVo.isAdd()){
            return playerService.userRelationPlayer(playerVo,getCurrentUser().getUser().getId());
        }else{
            return playerService.updateRelationPlayer(playerVo,getCurrentUser().getUser().getId());
        }

    }
}