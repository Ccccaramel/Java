package com.ding.hyld.controller;

import com.alibaba.fastjson.JSONObject;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.GearService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.ResourceUploadAndDownloadUtils;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.GearVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/gear")
public class GearController extends BaseController {
    @Autowired
    private GearService gearService;

    @GetMapping("/searchGear")
    public R searchGear(Page page, GearVo gearVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data", gearService.searchGear(page, gearVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(gearService.searchGear(null,gearVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PreAuthorize("hasAnyAuthority('gearManage_add','gearManage_update')")
    @PostMapping("/saveGear")
    public R saveGear(@RequestParam("gearVoStr") String gearVoStr, MultipartFile oneLevelImg, MultipartFile twoLevelImg, MultipartFile threeLevelImg){
        // 解析
        ObjectMapper objectMapper = new ObjectMapper();
        GearVo gearVo =objectMapper.convertValue(JSONObject.parse(gearVoStr), GearVo.class);

        // 1.将解析整理资源并存储,并返回资源信息
        ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(ResourcesPathUtils.getPhotoDirFile(), ResourcesPathUtils.getVideoDirFile(), ResourcesPathUtils.getAudioDirFile(), ResourcesPathUtils.getFileDirFile());
        if(oneLevelImg!=null){
            // 1.删除旧资源
            if(!gearVo.getOneLevelImg().isEmpty()){
                File oldFile = new File(ResourcesPathUtils.getRealPhotoPath()+gearVo.getOneLevelImg());
                oldFile.delete();
            }

            String oneLevelImgNewName = resourceUploadAndDownload.resourceUpload(oneLevelImg,getUserId()).get("newName");
            gearVo.setOneLevelImg(oneLevelImgNewName);
        }
        if(twoLevelImg!=null){
            // 1.删除旧资源
            if(!gearVo.getTwoLevelImg().isEmpty()){
                File oldFile = new File(ResourcesPathUtils.getRealPhotoPath()+gearVo.getTwoLevelImg());
                oldFile.delete();
            }

            String twoLevelImgNewName = resourceUploadAndDownload.resourceUpload(twoLevelImg,getUserId()).get("newName");
            gearVo.setTwoLevelImg(twoLevelImgNewName);
        }
        if(threeLevelImg!=null){
            // 1.删除旧资源
            if(!gearVo.getThreeLevelImg().isEmpty()){
                File oldFile = new File(ResourcesPathUtils.getRealPhotoPath()+gearVo.getThreeLevelImg());
                oldFile.delete();
            }

            String threeLevelImgNewName = resourceUploadAndDownload.resourceUpload(threeLevelImg,getUserId()).get("newName");
            gearVo.setThreeLevelImg(threeLevelImgNewName);
        }

        if(gearVo.isAdd()){
            gearService.add(gearVo);
            return R.success("新增强化装备成功!");
        }
        else{
            gearService.update(gearVo);
            return R.success("修改强化装备成功!");
        }
    }
}
