package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.MusicService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.ResourceUploadAndDownloadUtils;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.vo.MusicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/music")
public class MusicController extends BaseController {
    @Autowired
    private MusicService musicService;

    @PostMapping("/saveMusic")
    public R saveMusic(MusicVo musicVo, MultipartFile blogFile){
        HashMap<String,Object> result=new HashMap<>();

        if(!isLogin()){
            return R.fail("请先登录!");
        }

        if(!isRoot()){
            return R.fail("暂无权限!");
        }

        if(blogFile!=null){
            // 2.将解析整理资源并存储,并返回资源信息
//            ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(
//                    ResourcesPathUtils.BLOG,
//                    ResourcesPathUtils.getPhotoDirFile(ResourcesPathUtils.BLOG),
//                    ResourcesPathUtils.getVideoDirFile(ResourcesPathUtils.BLOG),
//                    ResourcesPathUtils.getAudioDirFile(ResourcesPathUtils.BLOG),
//                    ResourcesPathUtils.getFileDirFile(ResourcesPathUtils.BLOG));
//            Map<String, String> res = resourceUploadAndDownload.resourceUpload(blogFile, getUserId());
//            String originalName = res.get("fileName");
//            String linkName = res.get("newName");
//
//            musicVo.setAudio(originalName);
//            musicVo.setAudioLink(linkName);
//            musicVo.setStatus(DictionaryCode.BLOG_FILE_STATUS_1);
//
//            musicService.add(musicVo);

//            result.put("url",res.get("accessPath"));
            return R.success(result);
        }

        return R.fail("资源上传失败!请重试");
    }
}