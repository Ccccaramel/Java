package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.entity.Team;
import com.ding.hyld.entity.UserWithTeam;
import com.ding.hyld.info.SearchTeamInfo;
import com.ding.hyld.info.UserWithTeamInfo;
import com.ding.hyld.service.TeamService;
import com.ding.hyld.service.UserWithTeamService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/userWithTeam")
public class UserWithTeamController {
    @Autowired
    private UserWithTeamService userWithTeamService;
    @Autowired
    private TeamService teamService;

    /**
     * 用户绑定战队,并接手管理
     *   1.根据 id(uwt 的 id) 判断是编辑关联还是新增关联
     *     a.新增关联
     *       I.根据 teamScid 判断 team 是否存在
     *         <1>team 不存在,新增 team 并获取 teamId
     *           1)新增 team 并新增 uwt
     *         <2>team 存在,获取 teamId 并搜索 teamId 是否被绑定中
     *           1)未被绑定,新增 uwt
     *           2)已被绑定,返回错误信息
     *     b.编辑关联
     *       I.根据 id(uwt 的 id) 获取 uwt 判断是否存在
     *         <1>关联不存在,返回错误信息
     *         <2>关联存在,获取关联 和 team 并更新
     * @return
     */

    @PostMapping("/saveTeamInfo")
    public R saveTeamInfo(@RequestBody UserWithTeamInfo userWithTeamInfo){
        Integer userId=1;
        if(userWithTeamInfo.getId()==null){ // 新增关联
            Team team = teamService.findByScid(userWithTeamInfo.getTeamScid());
            if(team==null){ // 新增关联 > team 不存在
                Team newTeam = new Team();
                newTeam.setScid(userWithTeamInfo.getTeamScid());
                newTeam.setName(userWithTeamInfo.getTeamName());
                newTeam.setNote(userWithTeamInfo.getTeamNote());
                newTeam.setEliminationLine(userWithTeamInfo.getTeamEliminationLine());
                newTeam.setExcellentLine(userWithTeamInfo.getTeamExcellentLine());
                newTeam.setStatus(DictionaryCode.TEAM_STATUS_1);
                teamService.add(newTeam);

                UserWithTeam newUserWithTeam = new UserWithTeam();
                newUserWithTeam.setUserId(userId);
                newUserWithTeam.setTeamId(newTeam.getId());
                newUserWithTeam.setNote(userWithTeamInfo.getNote());
                newUserWithTeam.setStatus(DictionaryCode.USER_WITH_PLAYER_STATUS_2);
                userWithTeamService.add(newUserWithTeam); // 关联

                return R.fail("转队关联成功!");
            }
            else { // 新增关联 > team 存在
                List<UserWithTeam> teamList = userWithTeamService.findByTeamId(team.getId());
                if(teamList.size()>0){ // 已被关联中
                    UserWithTeam t = teamList.get(0);
                    if(Objects.equals(t.getUserId(), userId)){
                        return R.fail("你已关联该战队,请勿重复关联!");
                    }
                    return R.fail("战队关联失败,该战队已被其他用户关联!请进入QQ群(475765701)联系管理员!");
                }
                else{ // 新增关联 > team 存在 > team 未被关联
                    UserWithTeam newUserWithTeam = new UserWithTeam();
                    newUserWithTeam.setUserId(userId);
                    newUserWithTeam.setTeamId(team.getId());
                    newUserWithTeam.setNote(userWithTeamInfo.getNote());
                    newUserWithTeam.setStatus(DictionaryCode.USER_WITH_PLAYER_STATUS_2);
                    userWithTeamService.add(newUserWithTeam); // 关联
                    return R.success("战队关联成功!");
                }
            }
        }
        else{ // 编辑关联
            Team team = teamService.findById(userWithTeamInfo.getTeamId());
            UserWithTeam userWithTeam = userWithTeamService.getBy(userWithTeamInfo);
            if(team != null && userWithTeam != null){ // 编辑关联 > uwt team 均存在
                team.setNote(userWithTeamInfo.getTeamNote());
                team.setEliminationLine(userWithTeamInfo.getTeamEliminationLine());
                team.setExcellentLine(userWithTeamInfo.getTeamExcellentLine());
                teamService.update(team);

                userWithTeam.setNote(userWithTeamInfo.getUserNote());
                userWithTeamService.update(userWithTeam);

                return R.success("战队关联信息编辑成功!");
            }else{
                return R.success("战队关联信息编辑失败!信息不正确");
            }
        }
    }

    @PostMapping("/removeTeam")
    public R removeTeam(Integer teamId){
        Integer userId=1;
        userWithTeamService.removeTeam(userId,teamId);
        return R.success("已成功解除关联!");
    }

    @GetMapping("/searchTeam")
    public R searchTeam(Page page, SearchTeamInfo searchTeamInfo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",userWithTeamService.searchTeam(page,searchTeamInfo));
        if(Objects.equals(page,null)) {
            result.put("totalPage", Math.ceil(userWithTeamService.searchTeam(null, searchTeamInfo).size() * 1.0 / page.getSize()));
        }
        return R.success(result);
    }
}
