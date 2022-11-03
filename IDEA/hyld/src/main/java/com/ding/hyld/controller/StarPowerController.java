package com.ding.hyld.controller;

import com.alibaba.fastjson.JSONObject;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.StarPowerService;
import com.ding.hyld.service.UpdateLogService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.ResourceUploadAndDownloadUtils;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.vo.GameRoleVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.StarPowerVo;
import com.ding.hyld.vo.UpdateLogVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/starPower")
public class StarPowerController extends BaseController {
    @Autowired
    private StarPowerService starPowerService;

    @GetMapping("/searchStarPower")
    public R searchStarPower(Page page, StarPowerVo starPowerVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",starPowerService.searchStarPower(page, starPowerVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(starPowerService.searchStarPower(null,starPowerVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/saveStarPower")
    public R saveStarPower(@RequestParam("starPowerVoStr") String starPowerVoStr, MultipartFile image){
        // 解析
        ObjectMapper objectMapper = new ObjectMapper();
        StarPowerVo starPowerVo =objectMapper.convertValue(JSONObject.parse(starPowerVoStr),StarPowerVo.class);

        // 1.将解析整理资源并存储,并返回资源信息
        ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(ResourcesPathUtils.getPhotoDirFile(), ResourcesPathUtils.getVideoDirFile(), ResourcesPathUtils.getAudioDirFile(), ResourcesPathUtils.getFileDirFile());
        if(image!=null){
            // 1.删除旧资源
            if(!starPowerVo.getImage().isEmpty()){
                File oldFile = new File(ResourcesPathUtils.getRealPhotoPath()+starPowerVo.getImage());
                oldFile.delete();
            }

            String imageNewName = resourceUploadAndDownload.resourceUpload(image,getUserId()).get("newName");
            starPowerVo.setImage(imageNewName);
        }

        if(starPowerVo.isAdd()){
            starPowerService.add(starPowerVo);
            return R.success("新增星徽之力成功!");
        }
        else{
            starPowerService.update(starPowerVo);
            return R.success("修改星徽之力成功!");
        }
    }
}
