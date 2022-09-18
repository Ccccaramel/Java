package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.UserWithPlayerService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.ResourceUploadAndDownloadUtils;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UserWithPlayerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/userWithPlayer")
public class UserWithPlayerController extends BaseController {
    @Autowired
    private UserWithPlayerService userWithPlayerService;

    @GetMapping("/searchMyGameAccount")
    public R searchMyGameAccount(){
        UserWithPlayerVo userWithPlayerVo = new UserWithPlayerVo();
        userWithPlayerVo.setUserId(getCurrentUser().getUser().getId());
        userWithPlayerVo.setRelationStatus(DictionaryCode.RELATION_STATUS_2);
        return R.success(userWithPlayerService.searchRelation(userWithPlayerVo,null));
    }

    @GetMapping("/searchPlayerExamine")
    public R searchPlayerExamine(UserWithPlayerVo userWithPlayerVo,Page page){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",userWithPlayerService.searchRelation(userWithPlayerVo,page));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(userWithPlayerService.searchRelation(userWithPlayerVo,null).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

    /**
     * 用户解除与游戏账号的关联
     * @param playerId
     * @return
     */
    @GetMapping("/relievePlayer")
    public R relievePlayer(Integer playerId){
        userWithPlayerService.changeRelationStatus(playerId,getCurrentUser().getUser().getId(), DictionaryCode.RELATION_STATUS_3);
        return R.success("已解除关联!");
    }

    @PostMapping("/savePlayerCheckData")
    public R savePlayerCheckData(@RequestParam("relationId")Integer relationId,@RequestParam("playerMainPageFile") MultipartFile playerMainPageFile, @RequestParam("playerPreparePageFile") MultipartFile playerPreparePageFile){
        // 1.将解析整理资源并存储,并返回资源信息
        ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(ResourcesPathUtils.getPhotoDirFile(), ResourcesPathUtils.getVideoDirFile(), ResourcesPathUtils.getAudioDirFile(), ResourcesPathUtils.getFileDirFile());
        String playerMainPageNewName = resourceUploadAndDownload.resourceUpload(playerMainPageFile,getCurrentUser().getUser().getId()).get("newName");
        String playerPreparePageNewName = resourceUploadAndDownload.resourceUpload(playerPreparePageFile,getCurrentUser().getUser().getId()).get("newName");

        // 2.将资源信息存入表中
        userWithPlayerService.saveCheckInfo(relationId,playerMainPageNewName,playerPreparePageNewName,DictionaryCode.CHECK_STATUS_2);

        return R.success("验证信息已提交!请等待审核");
    }

    @PostMapping("/playerExamineCheck")
    public R playerExamineCheck(@RequestBody UserWithPlayerVo userWithPlayerVo){
        userWithPlayerService.updateCheckInfo(userWithPlayerVo);
        return R.success("审核修改提交成功!");
    }
}
