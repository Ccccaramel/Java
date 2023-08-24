package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.entity.UserWithPlayer;
import com.ding.hyld.info.PlayerInfo;
import com.ding.hyld.info.UserWithPlayerInfo;
import com.ding.hyld.info.UserWithTeamInfo;
import com.ding.hyld.service.PlayerService;
import com.ding.hyld.service.UserWithPlayerService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.ResourceUploadAndDownloadUtils;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.PlayerVo;
import com.ding.hyld.vo.UserWithPlayerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/userWithPlayer")
public class UserWithPlayerController extends BaseController {
    @Autowired
    private UserWithPlayerService userWithPlayerService;
    @Autowired
    private PlayerService playerService;

    @GetMapping("/searchMyGameAccount")
    public R searchMyGameAccount(Page page){
        HashMap<String,Object> result=new HashMap<>();
        UserWithPlayerVo userWithPlayerVo = new UserWithPlayerVo();
        userWithPlayerVo.setUserId(getUserId());
        userWithPlayerVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        result.put("data",userWithPlayerService.searchRelation(userWithPlayerVo,page));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(userWithPlayerService.searchRelationOfPage(userWithPlayerVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @GetMapping("/searchPlayerExamine")
    public R searchPlayerExamine(UserWithPlayerVo userWithPlayerVo,Page page){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",userWithPlayerService.searchRelation(userWithPlayerVo,page));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(userWithPlayerService.searchRelationOfPage(userWithPlayerVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    /**
     * 用户解除与游戏账号的关联
     * @param playerId
     * @return
     */
    @PreAuthorize("hasAuthority('myGameAccount_cancel')")
    @GetMapping("/relievePlayer")
    public R relievePlayer(Integer playerId){
        userWithPlayerService.changeRelationStatus(playerId,getUserId(), DictionaryCode.RELATION_STATUS_3);
        return R.success("已解除关联!");
    }

    /**
     * 用户上传关联验证信息
     * @param relationId
     * @param playerMainPageFile
     * @param playerPreparePageFile
     * @return
     */
    @PreAuthorize("hasAuthority('myGameAccount_upVerificationInformation')")
    @PostMapping("/saveRelationPlayerInfoCheckData")
    public R saveRelationPlayerInfoCheckData(@RequestParam("relationId")Integer relationId,@RequestParam("playerMainPageFile") MultipartFile playerMainPageFile, @RequestParam("playerPreparePageFile") MultipartFile playerPreparePageFile){
        UserWithPlayerInfo userWithPlayerInfo = userWithPlayerService.findById(relationId);
        // 1.删除旧资源
        if(StringUtils.hasText(userWithPlayerInfo.getPlayerMainPage())){
            File oldFile = new File(ResourcesPathUtils.getRealPhotoPath(ResourcesPathUtils.HYLD)+userWithPlayerInfo.getPlayerMainPage());
            oldFile.delete();
        }
        if(StringUtils.hasText(userWithPlayerInfo.getPlayerPreparePage())){
            File oldFile = new File(ResourcesPathUtils.getRealPhotoPath(ResourcesPathUtils.HYLD)+userWithPlayerInfo.getPlayerPreparePage());
            oldFile.delete();
        }

        // 1.将解析整理资源并存储,并返回资源信息
        ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(
                ResourcesPathUtils.HYLD,
                ResourcesPathUtils.getPhotoDirFile(ResourcesPathUtils.HYLD),
                ResourcesPathUtils.getVideoDirFile(ResourcesPathUtils.HYLD),
                ResourcesPathUtils.getAudioDirFile(ResourcesPathUtils.HYLD),
                ResourcesPathUtils.getFileDirFile(ResourcesPathUtils.HYLD));
        String playerMainPageNewName = resourceUploadAndDownload.resourceUpload(playerMainPageFile,getUserId()).get("newName");
        String playerPreparePageNewName = resourceUploadAndDownload.resourceUpload(playerPreparePageFile,getUserId()).get("newName");

        // 2.将资源信息存入表中
        userWithPlayerService.saveCheckInfo(relationId,playerMainPageNewName,playerPreparePageNewName,DictionaryCode.CHECK_STATUS_2);

        return R.success("验证信息已提交!请等待审核");
    }

    /**
     * 管理员审核 用户关联游戏账号验证信息
     * @param userWithPlayerVo
     * @return
     */
    @PreAuthorize("hasAuthority('playerExamine_check')")
    @PostMapping("/playerExamineCheck")
    public R playerExamineCheck(@RequestBody UserWithPlayerVo userWithPlayerVo){
        userWithPlayerService.updateCheckInfo(userWithPlayerVo);
        return R.success("审核修改提交成功!");
    }

    /**
     * 关联新游戏账号&编辑已关联且验证通过的游戏账号信息
     * @param userWithPlayerVo
     * @return
     */
    @PreAuthorize("hasAnyAuthority('myGameAccount_relation','myGameAccount_update')")
    @PostMapping("/saveRelationPlayerInfo")
    public R saveRelationPlayerInfo(@RequestBody UserWithPlayerVo userWithPlayerVo){
        userWithPlayerVo.setUserId(getUserId());
        PlayerVo playerVo = new PlayerVo();
        playerVo.setScid(userWithPlayerVo.getPlayerScid());
        playerVo.setName(userWithPlayerVo.getPlayerName());
        playerVo.setType(userWithPlayerVo.getPlayerType());
        if(userWithPlayerVo.isAdd()){ // 关联新游戏账号
            userWithPlayerVo.setPlayerId(playerService.userRelationPlayer(playerVo));
            userWithPlayerVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
            if(userWithPlayerService.findBy(userWithPlayerVo)!=null){
                return R.fail("你已绑定!请勿重复绑定!");
            }
            userWithPlayerVo.setCheckStatus(DictionaryCode.CHECK_STATUS_1);
            userWithPlayerService.add(userWithPlayerVo); // 新增 uwt
            return R.success("游戏账号绑定成功!");
        }else{ // 编辑已关联且验证通过游戏账号
            // 检查用户是否关联且验证通过
            PlayerInfo playerInfo = playerService.findBy(playerVo);
            if(playerInfo==null){ // 不存在的游戏账号
                return R.fail("非法操作!");
            }
            playerVo.setId(playerInfo.getId());
            userWithPlayerVo.setPlayerId(playerInfo.getId());
            userWithPlayerVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
            userWithPlayerVo.setCheckStatus(DictionaryCode.CHECK_STATUS_3);
            UserWithPlayer userWithPlayer = userWithPlayerService.findBy(userWithPlayerVo);
            if(userWithPlayer==null){
                return R.fail("非法操作!");
            }
            playerService.updateRelationPlayer(playerVo);
            return R.success("游戏账号信息修改成功!");
        }
    }
}
