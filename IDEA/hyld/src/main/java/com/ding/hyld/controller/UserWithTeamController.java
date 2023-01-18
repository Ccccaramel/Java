package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.entity.Team;
import com.ding.hyld.entity.UserWithTeam;
import com.ding.hyld.info.TeamInfo;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    @PreAuthorize("hasAnyAuthority('myTeam_relation','myTeam_update')")
    @PostMapping("/saveTeamRelationInfo")
    public R saveTeamRelationInfo(@RequestBody UserWithTeamVo userWithTeamVo){
        Integer userId=getUserId();
        if(userWithTeamVo.isAdd()){ // 新增关联
            TeamInfo teamInfo = teamService.findByScid(userWithTeamVo.getTeamScid());
            if(teamInfo==null){ // 新增关联 > team 不存在

                if(userWithTeamVo.getPlayerPosition().equals(DictionaryCode.PLAYER_POSITION_3)){ // 队员关联战队,战队不存在
                    return R.fail("你的战队暂未入驻平台,请先联系你战队的管理者入驻本平台");
                }

                TeamVo teamVo = new TeamVo();
                teamVo.setScid(userWithTeamVo.getTeamScid());
                teamVo.setName(userWithTeamVo.getTeamName());
                teamVo.setNote(userWithTeamVo.getTeamNote());
                teamVo.setEliminationLine(userWithTeamVo.getTeamEliminationLine());
                teamVo.setExcellentLine(userWithTeamVo.getTeamExcellentLine());
                teamVo.setType(userWithTeamVo.getTeamType());
                teamVo.setStatus(DictionaryCode.TEAM_STATUS_1);
                teamService.add(teamVo);

                UserWithTeamVo newUserWithTeamVo = new UserWithTeamVo();
                newUserWithTeamVo.setUserId(userId);
                newUserWithTeamVo.setTeamId(teamVo.getId());
                newUserWithTeamVo.setNote(userWithTeamVo.getUserNote());
                newUserWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
                newUserWithTeamVo.setPlayerPosition(DictionaryCode.PLAYER_POSITION_1);
                newUserWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_1);
                userWithTeamService.add(newUserWithTeamVo); // 关联
                return R.success("战队关联成功!");
            }
            else { // 新增关联 > team 存在


                userWithTeamVo.setUserId(userId);
                userWithTeamVo.setTeamId(teamInfo.getId());
                userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
                List<UserWithTeamInfo> userWithTeamInfoList = userWithTeamService.findBy(userWithTeamVo);
                if(userWithTeamInfoList.size()>0){ // 新增关联 > team 存在 > 已被关联中
                    UserWithTeamInfo userWithTeamInfo = userWithTeamInfoList.get(0);
                    if(Objects.equals(userWithTeamInfo.getUser().getId(), userId)){ // 新增关联 > team 存在 > 已被关联中 > 自己已关联
                        return R.fail("你已关联该战队,请勿重复关联!");
                    }
                }

                // 队员关联战队,战队存在,检查是否存在验证通过且关联中的 uwt
                if(userWithTeamVo.getPlayerPosition().equals(DictionaryCode.PLAYER_POSITION_3)){
                    userWithTeamVo.setUserId(null);
                    userWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
                    userWithTeamVo.setPlayerPosition(DictionaryCode.PLAYER_POSITION_1);
                    List<UserWithTeamInfo> res = userWithTeamService.findBy(userWithTeamVo); // 获取队长信息,主要为了获取 id
                    if(res.size()==1){
                        UserWithTeamVo newUserWithTeamVo = new UserWithTeamVo();
                        newUserWithTeamVo.setUserId(userId);
                        newUserWithTeamVo.setTeamId(teamInfo.getId());
                        newUserWithTeamVo.setParentId(res.get(0).getId());
                        newUserWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
                        newUserWithTeamVo.setPlayerPosition(DictionaryCode.PLAYER_POSITION_3);
                        newUserWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_5);
                        userWithTeamService.add(newUserWithTeamVo); // 关联
                        return R.success("战队关联成功!");
                    }
                    else{
                        return R.fail("数据异常,为了保证数据正确后台终止了你的操作,请进QQ群(475765701)联系群主!,一起纠正数据,谢谢!");
                    }

                }

                UserWithTeamVo newUserWithTeamVo = new UserWithTeamVo();
                newUserWithTeamVo.setUserId(userId);
                newUserWithTeamVo.setTeamId(teamInfo.getId());
                newUserWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
                newUserWithTeamVo.setPlayerPosition(DictionaryCode.PLAYER_POSITION_1);
                newUserWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_1);
                userWithTeamService.add(newUserWithTeamVo); // 关联
                return R.success("战队关联成功!");
            }
        }
        else{ // 编辑关联
            TeamInfo teamInfo = teamService.findById(userWithTeamVo.getTeamId());
            userWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
            userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
            List<UserWithTeamInfo> userWithTeamInfoList = userWithTeamService.findBy(userWithTeamVo);
            if(teamInfo != null && userWithTeamInfoList != null){ // 该用户与战队已关联且通过了审核
                TeamVo teamVo = new TeamVo();
                teamVo.setId(teamInfo.getId());
                teamVo.setScid(teamInfo.getScid());
                teamVo.setName(userWithTeamVo.getTeamName());
                teamVo.setStatus(teamInfo.getStatus().getId());
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

    // 解除关联
    @PreAuthorize("hasAuthority('myTeam_cancel')")
    @PostMapping("/relieveTeam")
    public R relieveTeam(UserWithTeamVo userWithTeamVo){
//        userWithTeamVo.setUserId(getUserId());
        userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        userWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);

        // 判断当前用户是否为队长
        UserWithTeamVo vo = new UserWithTeamVo();
        vo.setUserId(getUserId());
        vo.setTeamId(userWithTeamVo.getTeamId());
        vo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        vo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
//        vo.setPlayerPosition(DictionaryCode.PLAYER_POSITION_1);
        List<UserWithTeamInfo> list = userWithTeamService.findBy(vo);
        UserWithTeamInfo userWithTeamInfo;
        if(list.size()!=1){
            return R.fail("你与该战队绑定异常!禁止操作!");
        }
        else{
            userWithTeamInfo = list.get(0);
            if(!Objects.equals(userWithTeamInfo.getPlayerPositionType().getId(), DictionaryCode.PLAYER_POSITION_1) && !Objects.equals(userWithTeamInfo.getId(), userWithTeamVo.getId())){
                return R.fail("你不是该战队的队长,你无权解除其他用户关联!");
            }
        }

//        List<UserWithTeamInfo> userWithTeamInfoList = userWithTeamService.findBy(userWithTeamVo);
//        if(userWithTeamInfoList.size()!=1){ // 查询到该用户与某一战队存在多个绑定,禁止操作
//            return R.fail("该用户未关联战队!无需解除关联!");
//        }

        // 如果队长解除与战队的关联,那么还需要解除他任命的副队长与战队的关联
//        UserWithTeamInfo userWithTeamInfo = userWithTeamInfoList.get(0);
        if(Objects.equals(userWithTeamInfo.getPlayerPositionType().getId(), DictionaryCode.PLAYER_POSITION_1) && Objects.equals(userWithTeamInfo.getId(), userWithTeamVo.getId())){
            userWithTeamVo.setTeamId(userWithTeamInfo.getTeam().getId());
            userWithTeamVo.setAllRelieve(true); // 队长在解除战队关联时，同时把战队其他副队长的关联也解除
        }else{
            userWithTeamVo.setAllRelieve(false);
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
        userWithTeamVo.setUserId(getUserId()); // 查询当前用户所关联的战队信息
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
        userWithTeamVo.setUserId(getUserId()); // 查询当前用户所关联的战队信息
        userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        userWithTeamVo.setUserWithTeamStatus(DictionaryCode.TEAM_STATUS_1);
        result.put("data",userWithTeamService.searchTeam(page, userWithTeamVo));
        if(!Objects.equals(page.getSize(),null)) {
            result.put("totalPage", Math.ceil(userWithTeamService.searchTeamOfPage(userWithTeamVo) * 1.0 / page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/saveTeamCheckData")
    public R saveTeamCheckData(@RequestParam("relationId")Integer relationId, @RequestParam("controllerPreparePageFile") MultipartFile controllerPreparePageFile, @RequestParam("teamMainPageFile") MultipartFile teamMainPageFile){
        UserWithTeamInfo userWithTeamInfo = userWithTeamService.findById(relationId);
        // 1.删除旧资源
        if(StringUtils.hasText(userWithTeamInfo.getControllerPreparePage())){
            File oldFile = new File(ResourcesPathUtils.getRealPhotoPath()+userWithTeamInfo.getControllerPreparePage());
            oldFile.delete();
        }
        if(StringUtils.hasText(userWithTeamInfo.getTeamMainPage())){
            File oldFile = new File(ResourcesPathUtils.getRealPhotoPath()+userWithTeamInfo.getTeamMainPage());
            oldFile.delete();
        }

        // 1.将解析整理资源并存储,并返回资源信息
        ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(ResourcesPathUtils.getPhotoDirFile(), ResourcesPathUtils.getVideoDirFile(), ResourcesPathUtils.getAudioDirFile(), ResourcesPathUtils.getFileDirFile());
        String controllerPreparePageNewName = resourceUploadAndDownload.resourceUpload(controllerPreparePageFile,getUserId()).get("newName");
        String teamMainPageNewName = resourceUploadAndDownload.resourceUpload(teamMainPageFile,getUserId()).get("newName");

        // 2.将资源信息存入表中
        userWithTeamService.saveCheckInfo(relationId,controllerPreparePageNewName,teamMainPageNewName,DictionaryCode.CHECK_STATUS_2);

        return R.success("验证信息已提交!请等待审核");
    }

    @GetMapping("/searchTeamRelation")
    public R searchTeamRelation(UserWithTeamVo userWithTeamVo,Page page){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",userWithTeamService.searchTeam(page,userWithTeamVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(userWithTeamService.searchTeamOfPage(userWithTeamVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PreAuthorize("hasAuthority('teamRelationManage_check')")
    @PostMapping("/teamExamineCheck")
    public R teamExamineCheck(@RequestBody UserWithTeamVo userWithTeamVo){
        TeamInfo teamInfo = teamService.findByScid(userWithTeamVo.getTeamScid());
        userWithTeamVo.setTeamId(teamInfo.getId());
        userWithTeamService.teamExamineCheck(userWithTeamVo);
        return R.success("战队关联信息验证信息提交成功!");
    }

    @GetMapping("/getAllViceCaptain")
    public R getAllViceCaptain(UserWithTeamVo userWithTeamVo,Page page){
        userWithTeamVo.setUserId(getUserId());
        userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        userWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
        userWithTeamVo.setPlayerPosition(DictionaryCode.PLAYER_POSITION_1);
        if(userWithTeamService.searchTeamOfPage(userWithTeamVo)==0){ // 查看该用户与该战队是否关联且验证通过
            return R.fail("非法请求!");
        }
        HashMap<String,Object> result=new HashMap<>();
        userWithTeamVo.setUserId(null);
        userWithTeamVo.setId(null);
        userWithTeamVo.setPlayerPosition(DictionaryCode.PLAYER_POSITION_2);
        result.put("data",userWithTeamService.searchTeam(page,userWithTeamVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(userWithTeamService.searchTeamOfPage(userWithTeamVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/addViceCaptain")
    public R addViceCaptain(@RequestBody UserWithTeamVo userWithTeamVo){
        if(userService.findById(userWithTeamVo.getUserId())==null){
            return R.fail("不存在该用户!");
        }
        Integer viceCaptainUserId = userWithTeamVo.getUserId();
        if(viceCaptainUserId.equals(getUserId())){
            return R.fail("不可以将自己添加为副队长!");
        }
        userWithTeamVo.setParentId(userWithTeamVo.getId());
        userWithTeamVo.setUserId(getUserId());
        userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        userWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
        userWithTeamVo.setPlayerPosition(DictionaryCode.PLAYER_POSITION_1);
        if(userWithTeamService.searchTeamOfPage(userWithTeamVo)==0){ // 查看该用户与该战队的关系(队长,关联中,验证通过)
            return R.fail("非法请求!");
        }
        userWithTeamVo.setUserId(viceCaptainUserId);
        userWithTeamVo.setPlayerPosition(DictionaryCode.PLAYER_POSITION_2);
        if(userWithTeamService.searchTeamOfPage(userWithTeamVo)>0){ // 是否重复关联,查看该用户与该战队的关系(副队队长,关联中,验证通过)
            return R.fail("该用户已是副队长!请勿重复添加!");
        }
        userWithTeamService.addViceCaptain(userWithTeamVo);
        return R.success("副队长添加成功!");
    }

    @GetMapping("/searchValidTeamInfo")
    public R searchValidTeamInfo(Page page, UserWithTeamVo userWithTeamVo){
        HashMap<String,Object> result=new HashMap<>();
        userWithTeamVo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
        userWithTeamVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        userWithTeamVo.setPlayerPosition(DictionaryCode.PLAYER_POSITION_1);
        userWithTeamVo.setCreditScore(100);
        result.put("data",userWithTeamService.searchValidTeamInfo(page,userWithTeamVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(userWithTeamService.searchValidTeamInfoOfPage(userWithTeamVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/teamTransfer")
    public R teamTransfer(@RequestBody UserWithTeamVo userWithTeamVo){
        if(Objects.equals(userWithTeamVo.getUserId(), getUserId())){
            return R.fail("不可以选择自己!");
        }
        if(userService.findById(userWithTeamVo.getNewUserId())==null){
            return R.fail("该用户不存在!");
        }
        userWithTeamVo.setUserId(getUserId());
        return userWithTeamService.teamTransfer(userWithTeamVo);

    }

    @PreAuthorize("hasAuthority('teamRelationManage_updateTeamCreditScore')")
    @PostMapping("/teamCreditScoreSave")
    public R teamCreditScoreSave(@RequestBody UserWithTeamVo userWithTeamVo){
        userWithTeamService.updateTeamCreditScore(userWithTeamVo);
        return R.success("战队信誉积分修改成功!");

    }

}
