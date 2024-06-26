package com.ding.office.controller;

import com.alibaba.fastjson.JSONObject;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.GadgetService;
import com.ding.office.utils.R;
import com.ding.office.utils.ResourceUploadAndDownloadUtils;
import com.ding.office.utils.ResourcesPathUtils;
import com.ding.office.vo.GadgetVo;
import com.ding.office.vo.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

/**
 * 随身妙具
 */
@RestController
@RequestMapping("/gadget")
public class GadgetController extends BaseController {
    @Autowired
    private GadgetService gadgetService;

    @GetMapping("/searchGadget")
    public R searchGadget(Page page, GadgetVo gadgetVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",gadgetService.searchGadget(page, gadgetVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(gadgetService.searchGadgetOfPage(gadgetVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PreAuthorize("hasAnyAuthority('gadgetManage_add','gadgetManage_update')")
    @PostMapping("/saveGadget")
    public R saveGadget(@RequestParam("gadgetVoStr") String gadgetVoStr, MultipartFile image){
        // 解析
        ObjectMapper objectMapper = new ObjectMapper();
        GadgetVo gadgetVo =objectMapper.convertValue(JSONObject.parse(gadgetVoStr),GadgetVo.class);

        // 1.将解析整理资源并存储,并返回资源信息
        ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(
                ResourcesPathUtils.HYLD,
                ResourcesPathUtils.getPhotoDirFile(ResourcesPathUtils.HYLD),
                ResourcesPathUtils.getVideoDirFile(ResourcesPathUtils.HYLD),
                ResourcesPathUtils.getAudioDirFile(ResourcesPathUtils.HYLD),
                ResourcesPathUtils.getFileDirFile(ResourcesPathUtils.HYLD));
        if(image!=null){
            // 1.删除旧资源
            if(StringUtils.hasText(gadgetVo.getImage())){
                File oldFile = new File(ResourcesPathUtils.getRealPhotoPath(ResourcesPathUtils.HYLD)+gadgetVo.getImage());
                oldFile.delete();
            }

            String imageNewName = resourceUploadAndDownload.resourceUpload(image,getUserId()).get("newName");
            gadgetVo.setImage(imageNewName);
        }

        if(gadgetVo.isAdd()){
            gadgetService.add(gadgetVo);
            return R.success("新增星徽之力成功!");
        }
        else{
            gadgetService.update(gadgetVo);
            return R.success("修改星徽之力成功!");
        }
    }
}
