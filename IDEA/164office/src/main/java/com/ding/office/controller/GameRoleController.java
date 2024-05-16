package com.ding.office.controller;

import com.alibaba.fastjson.JSONObject;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.entity.Dictionary;
import com.ding.office.info.GameRoleInfo;
import com.ding.office.service.DictionaryService;
import com.ding.office.service.GameRoleService;
import com.ding.office.utils.R;
import com.ding.office.utils.ResourceUploadAndDownloadUtils;
import com.ding.office.utils.ResourcesPathUtils;
import com.ding.office.vo.GameRoleVo;
import com.ding.office.vo.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@RestController
@RequestMapping("/gameRole")
public class GameRoleController extends BaseController {
    @Autowired
    private GameRoleService gameRoleService;

    @Autowired
    private DictionaryService dictionaryService;

    @GetMapping("/searchGameRole")
    public R searchGameRole(Page page, GameRoleVo gameRoleVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",gameRoleService.searchGameRole(page, gameRoleVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(gameRoleService.searchGameRoleOfPage(gameRoleVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PreAuthorize("hasAnyAuthority('gameRoleManage_add','gameRoleManage_update')")
    @PostMapping("/saveGameRole")
    public R saveGameRole(@RequestParam("gameRoleVoStr") String gameRoleVoStr, MultipartFile headImg, MultipartFile portrait){
        // 解析
        ObjectMapper objectMapper = new ObjectMapper();
        GameRoleVo gameRoleVo =objectMapper.convertValue(JSONObject.parse(gameRoleVoStr),GameRoleVo.class);

        // 1.将解析整理资源并存储,并返回资源信息
        ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(
                ResourcesPathUtils.HYLD,
                ResourcesPathUtils.getPhotoDirFile(ResourcesPathUtils.HYLD),
                ResourcesPathUtils.getVideoDirFile(ResourcesPathUtils.HYLD),
                ResourcesPathUtils.getAudioDirFile(ResourcesPathUtils.HYLD),
                ResourcesPathUtils.getFileDirFile(ResourcesPathUtils.HYLD));
        if(headImg!=null){
            // 1.删除旧资源
            if(StringUtils.hasText(gameRoleVo.getHeadImg())){
                File oldFile = new File(ResourcesPathUtils.getRealPhotoPath(ResourcesPathUtils.HYLD)+gameRoleVo.getHeadImg());
                oldFile.delete();
            }

            String headImgNewName = resourceUploadAndDownload.resourceUpload(headImg,getUserId()).get("newName");
            gameRoleVo.setHeadImg(headImgNewName);
        }
        if(portrait!=null){
            // 1.删除旧资源
            if(StringUtils.hasText(gameRoleVo.getPortrait())){
                File oldFile = new File(ResourcesPathUtils.getRealPhotoPath(ResourcesPathUtils.HYLD)+gameRoleVo.getPortrait());
                oldFile.delete();
            }

            String portraitNewName = resourceUploadAndDownload.resourceUpload(portrait,getUserId()).get("newName");
            gameRoleVo.setPortrait(portraitNewName);
        }

        if(gameRoleVo.isAdd()){
            gameRoleService.add(gameRoleVo);
            return R.success("新增游戏角色成功!");
        }
        else{
            gameRoleService.update(gameRoleVo);
            return R.success("修改游戏角色成功!");
        }
    }


    @GetMapping("/getAllGameRole")
    public R getAllGameRole(){
        return R.success(gameRoleService.getAllGameRole());
    }

    @GetMapping("/getBasicForm")
    public R getBasicForm(GameRoleVo gameRoleVo){
        return R.success(gameRoleService.getBasicForm(gameRoleVo));
    }

    @GetMapping("/searchGameRoleByClass")
    public R searchGameRoleByClass(){
        Map<String,Object> result = new HashMap<>();
        List<String> gameRoleRarityName = new ArrayList<>();
        List<List<GameRoleInfo>> gameRoleData = new ArrayList<>();
        for (Dictionary gameRoleRarity:dictionaryService.findByType("gameRoleRarity", null)) {
            GameRoleVo gameRoleVo = new GameRoleVo();
            gameRoleVo.setRarity(gameRoleRarity.getId());
            gameRoleRarityName.add(gameRoleRarity.getName());
            gameRoleData.add(gameRoleService.getBasicForm(gameRoleVo));
        }
        result.put("gameRoleRarityNameList",gameRoleRarityName);
        result.put("gameRoleData",gameRoleData);
        return R.success(result);
    }

    @GetMapping("/searchGameRoleInfoById")
    public R searchGameRoleInfoById(GameRoleVo gameRoleVo){
        return R.success(gameRoleService.searchGameRoleInfoById(gameRoleVo));
    }
}
