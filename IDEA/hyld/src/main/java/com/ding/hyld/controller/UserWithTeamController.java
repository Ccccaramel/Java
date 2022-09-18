package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.entity.Team;
import com.ding.hyld.entity.UserWithTeam;
import com.ding.hyld.info.UserWithTeamInfo;
import com.ding.hyld.service.UserService;
import com.ding.hyld.utils.ResourceUploadAndDownloadUtils;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.vo.TeamVo;
import com.ding.hyld.vo.UserWithTeamVo;
import com.ding.hyld.service.TeamService;
import com.ding.hyld.service.UserWithTeamService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/userWithTeam")
public class UserWithTeamController extends BaseController {
    @Autowired
    private UserWithTeamService userWithTeamService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;

    /**
     * 用户绑定战队(但还需验证通过才可接手管理)
     *   1.根据 teamScid 判断 team 是否存在
     *      <1>team 不存在,新增 team 并获取 teamId 并新增 uwt
     *      <2>team 存在,新增 uwt
     * @return
     */

    @PostMapping("/saveTeamRelationInfo")
    public R saveTeamRelationInfo(@RequestBody UserWithTeamVo userWithTeamVo){
        Integer userId=getCurrentUser().getUser().getId();
        if(userWithTeamVo.isAdd()){ // 新增关联
            Team team = teamService.findByScid(userWithTeamVo.getTeamScid());
            if(team==null){ // 新增关联 > team 不存在
                Team newTeam = new Team();
                newTeam.setScid(userWithTeamVo.getTeamScid());
                newTeam.setName(userWithTeamVo.getTeamName());
                newTeam.setNote(userWithTeamVo.getTeamNote());
                newTeam.setEliminationLine(userWithTeamVo.getTeamEliminationLine());
                newTeam.setExcellentLine(userWithTeamVo.getTeamExcellentLine());
                newTeam.setType(userWithTeamVo.getTeamType());
                newTeam.setStatus(DictionaryCode.TEAM_STATUS_1);
                teamService.add(newTeam);

                UserWithTeam newUserWithTeam = new UserWithTeam();
                newUserWithTeam.setUserId(userId);
                newUserWithTeam.setTeamId(newTeam.getId());
                newUserWithTeam.setNote(userWithTeamVo.getUserNote());
                newUserWithTeam.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
                newUserWithTeam.setPlayerPosition(DictionaryCode.PLAYER_POSITION_1);
                newUserWithTeam.setCheckStatus(DictionaryCode.CHECK_STATUS_1);
                userWithTeamService.add(newUserWithTeam); // 关联
                return R.success("战队关联成功!");
            }
            else { // 新增关联 > team 存在
                userWithTeamVo.setUserId(userId);
                userWithTeamVo.setTeamId(team.getId());
                userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
                List<UserWithTeamInfo> userWithTeamInfoList = userWithTeamService.findBy(userWithTeamVo);
                if(userWithTeamInfoList.size()>0){ // 新增关联 > team 存在 > 已被关联中
                    UserWithTeamInfo userWithTeamInfo = userWithTeamInfoList.get(0);
                    if(Objects.equals(userWithTeamInfo.getUser().getId(), userId)){ // 新增关联 > team 存在 > 已被关联中 > 自己已关联
                        return R.fail("你已关联该战队,请勿重复关联!");
                    }
                }
                UserWithTeam newUserWithTeam = new UserWithTeam();
                newUserWithTeam.setUserId(userId);
                newUserWithTeam.setTeamId(team.getId());
                newUserWithTeam.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
                newUserWithTeam.setPlayerPosition(DictionaryCode.PLAYER_POSITION_1);
                newUserWithTeam.setCheckStatus(DictionaryCode.CHECK_STATUS_1);
                userWithTeamService.add(newUserWithTeam); // 关联
                return R.success("战队关联成功!");
            }
        }
        else{ // 编辑关联
            Team team = teamService.findById(userWithTeamVo.getTeamId());
            userWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
            userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
            List<UserWithTeamInfo> userWithTeamInfoList = userWithTeamService.findBy(userWithTeamVo);
            if(team != null && userWithTeamInfoList != null){ // 该用户与战队已关联且通过了审核
                TeamVo teamVo = new TeamVo();
                teamVo.setId(team.getId());
                teamVo.setScid(team.getScid());
                teamVo.setName(userWithTeamVo.getTeamName());
                teamVo.setStatus(team.getStatus());
                teamVo.setNote(userWithTeamVo.getTeamNote());
                teamVo.setEliminationLine(userWithTeamVo.getTeamEliminationLine());
                teamVo.setExcellentLine(userWithTeamVo.getTeamExcellentLine());
                teamVo.setType(userWithTeamVo.getTeamType());
                teamService.update(teamVo);

                return R.success("战队关联信息编辑成功!");
            }else{
                return R.success("战队关联信息编辑失败!信息不正确");
            }
        }
    }

    @PostMapping("/relieveTeam")
    public R relieveTeam(UserWithTeamVo userWithTeamVo){
        userWithTeamVo.setUserId(getCurrentUser().getUser().getId());
        userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        userWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
        List<UserWithTeamInfo> userWithTeamInfoList = userWithTeamService.findBy(userWithTeamVo);
        if(userWithTeamInfoList.size()!=1){ // 查看该用户与该战队的关系是否关联中且验证通过
            return R.fail("非法请求!");
        }
        UserWithTeamInfo userWithTeamInfo = userWithTeamInfoList.get(0);
        if(Objects.equals(userWithTeamInfo.getPlayerPositionType().getId(), DictionaryCode.PLAYER_POSITION_2)){ // 判断是否为队长,如果是副队长则为非法请求
            userWithTeamVo.setAllRelieve(false);
        }else{
            userWithTeamVo.setTeamId(userWithTeamInfo.getTeam().getId());
            userWithTeamVo.setAllRelieve(true);
        }
        userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_3);
        userWithTeamService.relieveTeam(userWithTeamVo);
        return R.success("已成功解除关联!");
    }

    /**
     * 用户查询自己关联且验证通过的战队
     * @param page
     * @param userWithTeamVo
     * @return
     */
    @GetMapping("/searchMyTeam")
    public R searchMyTeam(Page page, UserWithTeamVo userWithTeamVo){
        HashMap<String,Object> result=new HashMap<>();
        userWithTeamVo.setUserId(getCurrentUser().getUser().getId()); // 查询当前用户所关联的战队信息
        userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        userWithTeamVo.setUserWithTeamStatus(DictionaryCode.TEAM_STATUS_1);
        userWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
        result.put("data",userWithTeamService.searchTeam(page, userWithTeamVo));
        if(!Objects.equals(page.getSize(),null)) {
            result.put("totalPage", Math.ceil(userWithTeamService.searchTeam(null, userWithTeamVo).size() * 1.0 / page.getSize()));
        }
        return R.success(result);
    }

    /**
     * 用户查询自己关联的战队
     * @param page
     * @param userWithTeamVo
     * @return
     */
    @GetMapping("/searchMyRelationTeam")
    public R searchMyRelationTeam(Page page, UserWithTeamVo userWithTeamVo){
        HashMap<String,Object> result=new HashMap<>();
        userWithTeamVo.setUserId(getCurrentUser().getUser().getId()); // 查询当前用户所关联的战队信息
        userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        userWithTeamVo.setUserWithTeamStatus(DictionaryCode.TEAM_STATUS_1);
        result.put("data",userWithTeamService.searchTeam(page, userWithTeamVo));
        if(!Objects.equals(page.getSize(),null)) {
            result.put("totalPage", Math.ceil(userWithTeamService.searchTeam(null, userWithTeamVo).size() * 1.0 / page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/saveTeamCheckData")
    public R saveTeamCheckData(@RequestParam("relationId")Integer relationId, @RequestParam("controllerPreparePageFile") MultipartFile controllerPreparePageFile, @RequestParam("teamMainPageFile") MultipartFile teamMainPageFile){
        // 1.将解析整理资源并存储,并返回资源信息
        ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(ResourcesPathUtils.getPhotoDirFile(), ResourcesPathUtils.getVideoDirFile(), ResourcesPathUtils.getAudioDirFile(), ResourcesPathUtils.getFileDirFile());
        String controllerPreparePageNewName = resourceUploadAndDownload.resourceUpload(controllerPreparePageFile,getCurrentUser().getUser().getId()).get("newName");
        String teamMainPageNewName = resourceUploadAndDownload.resourceUpload(teamMainPageFile,getCurrentUser().getUser().getId()).get("newName");

        // 2.将资源信息存入表中
        userWithTeamService.saveCheckInfo(relationId,controllerPreparePageNewName,teamMainPageNewName,DictionaryCode.CHECK_STATUS_2);

        return R.success("验证信息已提交!请等待审核");
    }

    @GetMapping("/searchTeamExamine")
    public R searchTeamExamine(UserWithTeamVo userWithTeamVo,Page page){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",userWithTeamService.searchTeam(page,userWithTeamVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(userWithTeamService.searchTeam(null,userWithTeamVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/teamExamineCheck")
    public R teamExamineCheck(@RequestBody UserWithTeamVo userWithTeamVo){
        userWithTeamService.teamExamineCheck(userWithTeamVo);
        return R.success("战队关联信息验证信息提交成功!");
    }

    @GetMapping("/getAllViceCaptain")
    public R getAllViceCaptain(UserWithTeamVo userWithTeamVo,Page page){
        userWithTeamVo.setUserId(getCurrentUser().getUser().getId());
        userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        userWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
        userWithTeamVo.setPlayerPosition(DictionaryCode.PLAYER_POSITION_1);
        if(userWithTeamService.searchTeam(null,userWithTeamVo).size()==0){ // 查看该用户与该战队是否关联且验证通过
            return R.fail("非法请求!");
        }
        HashMap<String,Object> result=new HashMap<>();
        userWithTeamVo.setUserId(null);
        userWithTeamVo.setId(null);
        userWithTeamVo.setPlayerPosition(DictionaryCode.PLAYER_POSITION_2);
        result.put("data",userWithTeamService.searchTeam(page,userWithTeamVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(userWithTeamService.searchTeam(null,userWithTeamVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/addViceCaptain")
    public R addViceCaptain(@RequestBody UserWithTeamVo userWithTeamVo){
        if(userService.findById(userWithTeamVo.getUserId())==null){
            return R.fail("不存在该用户!");
        }
        Integer viceCaptainUserId = userWithTeamVo.getUserId();
        if(viceCaptainUserId.equals(getCurrentUser().getUser().getId())){
            return R.fail("不可以将自己添加为副队长!");
        }
        userWithTeamVo.setUserId(getCurrentUser().getUser().getId());
        userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        userWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
        userWithTeamVo.setPlayerPosition(DictionaryCode.PLAYER_POSITION_1);
        if(userWithTeamService.searchTeam(null,userWithTeamVo).size()==0){ // 查看该用户与该战队的关系(队长,关联中,验证通过)
            return R.fail("非法请求!");
        }
        userWithTeamVo.setUserId(viceCaptainUserId);
        userWithTeamVo.setPlayerPosition(DictionaryCode.PLAYER_POSITION_2);
        if(userWithTeamService.searchTeam(null,userWithTeamVo).size()>0){ // 是否重复关联,查看该用户与该战队的关系(副队队长,关联中,验证通过)
            return R.fail("该用户已是副队长!请勿重复添加!");
        }
        userWithTeamService.addViceCaptain(userWithTeamVo);
        return R.success("副队长添加成功!");
    }
}
