package com.ding.office.controller;

import com.ding.office.constant.DictionaryCode;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.BlogFileService;
import com.ding.office.utils.R;
import com.ding.office.utils.ResourceUploadAndDownloadUtils;
import com.ding.office.utils.ResourcesPathUtils;
import com.ding.office.vo.BlogFileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/blogFile")
public class BlogFileController extends BaseController {
    @Autowired
    private BlogFileService blogFileService;

    @PostMapping("/saveBlogFile")
    public R saveBlogFile(BlogFileVo blogFileVo, MultipartFile blogFile){
        HashMap<String,Object> result=new HashMap<>();

        if(!isLogin()){
            return R.fail("请先登录!");
        }

        if(blogFile!=null){
            // 2.将解析整理资源并存储,并返回资源信息
            ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(
                    ResourcesPathUtils.BLOG,
                    ResourcesPathUtils.getPhotoDirFile(ResourcesPathUtils.BLOG),
                    ResourcesPathUtils.getVideoDirFile(ResourcesPathUtils.BLOG),
                    ResourcesPathUtils.getAudioDirFile(ResourcesPathUtils.BLOG),
                    ResourcesPathUtils.getFileDirFile(ResourcesPathUtils.BLOG));
            Map<String, String> res = resourceUploadAndDownload.resourceUpload(blogFile, getUserId());
            String originalName = res.get("fileName");
            String linkName = res.get("newName");

            blogFileVo.setUser(getUserId());
            blogFileVo.setOriginalName(originalName);
            blogFileVo.setLinkName(linkName);
            blogFileVo.setStatus(DictionaryCode.BLOG_FILE_STATUS_1);

            blogFileService.add(blogFileVo);

            result.put("url",res.get("accessPath"));
            return R.success(result);
        }

        return R.fail("资源上传失败!请重试");
    }
}