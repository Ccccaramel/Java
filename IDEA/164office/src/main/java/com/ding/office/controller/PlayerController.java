package com.ding.office.controller;

import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.PlayerService;
import com.ding.office.utils.R;
import com.ding.office.vo.Page;
import com.ding.office.vo.PlayerVo;
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
            result.put("totalPage",Math.ceil(playerService.searchPlayerInfoOfPage(playerVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    /**
     * 管理员修改游戏账号的信息
     * @param playerVo
     * @return
     */
    @PreAuthorize("hasAuthority('playerManage_update')")
    @PostMapping("/updatePlayer")
    public R updatePlayer(@RequestBody PlayerVo playerVo){
        playerService.updatePlayer(playerVo);
        return R.success("游戏账号信息修改成功!");
    }
}
