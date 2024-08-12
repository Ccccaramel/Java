package com.ding.office.controller;

import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.ResourceService;
import com.ding.office.utils.R;
import com.ding.office.utils.ResourceUploadAndDownloadUtils;
import com.ding.office.utils.ResourcesPathUtils;
import com.ding.office.vo.ResourceVo;
import com.ding.office.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/resource")
public class ResourceController extends BaseController {
    @Autowired
    private ResourceService resourceService;

    @GetMapping("/searchResource")
    public R searchResource(Page page, ResourceVo resourceVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data", resourceService.searchResource(page, resourceVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(resourceService.searchResourceOfPage(resourceVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PreAuthorize("hasAnyAuthority('resourceManage_add','resourceManage_update')")
    @PostMapping("/saveResource")
    public R saveResource(ResourceVo resourceVo, MultipartFile resourceFile){
        if(resourceFile!=null){
            // 1.删除旧资源
            if(StringUtils.hasText(resourceVo.getFileName())){
                File oldFile = new File(ResourcesPathUtils.getRealPhotoPath(ResourcesPathUtils.HYLD)+ resourceVo.getFileName());
                oldFile.delete();
            }

            // 2.将解析整理资源并存储,并返回资源信息
            ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(
                    ResourcesPathUtils.HYLD,
                    ResourcesPathUtils.getPhotoDirFile(ResourcesPathUtils.HYLD),
                    ResourcesPathUtils.getVideoDirFile(ResourcesPathUtils.HYLD),
                    ResourcesPathUtils.getAudioDirFile(ResourcesPathUtils.HYLD),
                    ResourcesPathUtils.getFileDirFile(ResourcesPathUtils.HYLD));
            String resourceNewName = resourceUploadAndDownload.resourceUpload(resourceFile,getUserId()).get("newName");

            resourceVo.setFileName(resourceNewName);
        }


        if(resourceVo.isAdd()){
            resourceService.add(resourceVo);
            return R.success("新增头像成功!");
        }
        else{
            resourceService.update(resourceVo);
            return R.success("修改头像成功!");
        }
    }

    @PreAuthorize("hasAuthority('resourceManage_delete')")
    @PostMapping("/deleteResource")
    public R deleteResource(@RequestBody ResourceVo resourceVo){
        // 1.删除旧资源
        if(StringUtils.hasText(resourceVo.getFileName())){
            File oldFile = new File(ResourcesPathUtils.getRealPhotoPath(ResourcesPathUtils.HYLD)+ resourceVo.getFileName());
            oldFile.delete();
        }
        resourceService.del(resourceVo);
        return R.success("头像成功删除!");
    }
}