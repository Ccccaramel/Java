package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.BlogFileService;
import com.ding.hyld.service.ChatFileService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.ResourceUploadAndDownloadUtils;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.vo.BlogFileVo;
import com.ding.hyld.vo.ChatFileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chatFile")
public class ChatFileController extends BaseController {
    @Autowired
    private ChatFileService chatFileService;

    @PostMapping("/saveChatFile")
    public R saveBlogFile(ChatFileVo chatFileVo, MultipartFile chatFile){
        HashMap<String,Object> result=new HashMap<>();

        if(!isLogin()){
            return R.fail("请先登录!");
        }

        if(chatFile!=null){
            // 2.将解析整理资源并存储,并返回资源信息
            ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(
                    ResourcesPathUtils.CHAT,
                    ResourcesPathUtils.getPhotoDirFile(ResourcesPathUtils.CHAT),
                    ResourcesPathUtils.getVideoDirFile(ResourcesPathUtils.CHAT),
                    ResourcesPathUtils.getAudioDirFile(ResourcesPathUtils.CHAT),
                    ResourcesPathUtils.getFileDirFile(ResourcesPathUtils.CHAT));
            Map<String, String> res = resourceUploadAndDownload.resourceUpload(chatFile, getUserId());
            String originalName = res.get("fileName");
            String linkName = res.get("newName");

            chatFileVo.setSender(getUserId());
            chatFileVo.setOriginalName(originalName);
            if(chatFileVo.getRecipient()==0){
                chatFileVo.setRecipient(getUserId());
            }
            chatFileVo.setLinkName(linkName);
            chatFileVo.setStatus(DictionaryCode.BLOG_FILE_STATUS_1);

            chatFileService.add(chatFileVo);

            result.put("url",res.get("accessPath"));
            return R.success(result);
        }

        return R.fail("资源上传失败!请重试");
    }
}