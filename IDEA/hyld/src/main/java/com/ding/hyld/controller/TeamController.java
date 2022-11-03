package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.service.TeamService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TeamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService teamService;

//    @GetMapping("/searchTeamInfo")
//    public R searchTeamInfo(Page page, TeamVo teamVo){
//        HashMap<String,Object> result=new HashMap<>();
//        teamVo.setStatus(DictionaryCode.TEAM_STATUS_1);
//        result.put("data",teamService.searchTeamInfo(page,teamVo));
//        if(!Objects.equals(page.getSize(),null)){
//            result.put("totalPage",Math.ceil(teamService.searchTeamInfo(null,teamVo).size()*1.0/page.getSize()));
//        }
//        return R.success(result);
//    }

    /**
     * 管理员查询所有战队信息
     * @param page
     * @param teamVo
     * @return
     */
    @GetMapping("/searchAllTeam")
    public R searchAllTeam(Page page, TeamVo teamVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",teamService.searchTeamInfo(page, teamVo));
        if(!Objects.equals(page.getSize(),null)) {
            result.put("totalPage", Math.ceil(teamService.searchTeamInfo(null, teamVo).size() * 1.0 / page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/saveTeamInfo")
    public R saveTeamInfo(@RequestBody TeamVo teamVo){
        teamService.update(teamVo);
        return R.success("战队信息保存成功!");
    }
}
