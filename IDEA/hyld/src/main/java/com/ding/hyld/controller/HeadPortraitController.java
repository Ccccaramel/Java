package com.ding.hyld.controller;

import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.HeadPortraitService;
import com.ding.hyld.service.UpdateLogService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.ResourceUploadAndDownloadUtils;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.vo.HeadPortraitVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UpdateLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/headPortrait")
public class HeadPortraitController extends BaseController {
    @Autowired
    private HeadPortraitService headPortraitService;

    @GetMapping("/searchHeadPortrait")
    public R searchHeadPortrait(Page page, HeadPortraitVo headPortraitVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",headPortraitService.searchHeadPortrait(page, headPortraitVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(headPortraitService.searchHeadPortraitOfPage(headPortraitVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PreAuthorize("hasAnyAuthority('headPortraitManage_add','headPortraitManage_update')")
    @PostMapping("/saveHeadPortrait")
    public R saveHeadPortrait(HeadPortraitVo headPortraitVo, MultipartFile headPortraitFile){
        if(headPortraitFile!=null){
            // 1.删除旧资源
            if(StringUtils.hasText(headPortraitVo.getImage())){
                File oldFile = new File(ResourcesPathUtils.getRealPhotoPath(ResourcesPathUtils.HYLD)+headPortraitVo.getImage());
                oldFile.delete();
            }

            // 2.将解析整理资源并存储,并返回资源信息
            ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(
                    ResourcesPathUtils.HYLD,
                    ResourcesPathUtils.getPhotoDirFile(ResourcesPathUtils.HYLD),
                    ResourcesPathUtils.getVideoDirFile(ResourcesPathUtils.HYLD),
                    ResourcesPathUtils.getAudioDirFile(ResourcesPathUtils.HYLD),
                    ResourcesPathUtils.getFileDirFile(ResourcesPathUtils.HYLD));
            String headPortraitNewName = resourceUploadAndDownload.resourceUpload(headPortraitFile,getUserId()).get("newName");

            headPortraitVo.setImage(headPortraitNewName);
        }


        if(headPortraitVo.isAdd()){
            headPortraitService.add(headPortraitVo);
            return R.success("新增头像成功!");
        }
        else{
            headPortraitService.update(headPortraitVo);
            return R.success("修改头像成功!");
        }
    }

    @PreAuthorize("hasAuthority('headPortraitManage_delete')")
    @PostMapping("/deleteHeadPortrait")
    public R deleteHeadPortrait(@RequestBody HeadPortraitVo headPortraitVo){
        // 1.删除旧资源
        if(StringUtils.hasText(headPortraitVo.getImage())){
            File oldFile = new File(ResourcesPathUtils.getRealPhotoPath(ResourcesPathUtils.HYLD)+headPortraitVo.getImage());
            oldFile.delete();
        }
        headPortraitService.del(headPortraitVo);
        return R.success("头像成功删除!");
    }
}