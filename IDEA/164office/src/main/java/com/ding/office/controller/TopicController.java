package com.ding.office.controller;

import com.alibaba.fastjson.JSONObject;
import com.ding.office.constant.DictionaryCode;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.info.TopicInfo;
import com.ding.office.security.CurrentUser;
import com.ding.office.service.TopicService;
import com.ding.office.service.impl.QQIPServiceImpl;
import com.ding.office.utils.IpUtils;
import com.ding.office.utils.R;
import com.ding.office.utils.ResourceUploadAndDownloadUtils;
import com.ding.office.utils.ResourcesPathUtils;
import com.ding.office.vo.Page;
import com.ding.office.vo.TopicVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/topic")
public class TopicController extends BaseController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private QQIPServiceImpl ibsService;
    /**
     * 用于搜索话题
     * @param page
     * @param topicVo
     * @return
     */
    @GetMapping("/searchTopic")
    public R searchTopic(Page page, TopicVo topicVo){
        HashMap<String,Object> result=new HashMap<>();
        if(topicVo.isShow() || !(isLogin() && getCurrentUser().getUser().getRole().equals(DictionaryCode.USER_ROLE_1))){
            topicVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        }
        result.put("data",topicService.searchTopic(page, topicVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(topicService.searchTopicOfPage(topicVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    /**
     * 用于搜索我的话题
     * @param page
     * @param topicVo
     * @return
     */
    @GetMapping("/searchMyTopic")
    public R searchMyTopic(Page page, TopicVo topicVo){
        HashMap<String,Object> result=new HashMap<>();
        topicVo.setUserId(getUserId());
        topicVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        result.put("data",topicService.searchTopic(page, topicVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(topicService.searchTopicOfPage(topicVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    /**
     * 获取话题的回复
     * @param page
     * @param topicVo
     * @return
     */
    @GetMapping("/getTopicReply")
    public R getTopicReply(Page page, TopicVo topicVo){
        HashMap<String,Object> result=new HashMap<>();
        CurrentUser currentUser = getCurrentUser();
        if(topicVo.isManageMyTopic() || currentUser==null || currentUser.getUser().getRole().equals(DictionaryCode.USER_ROLE_2)){
            topicVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
            topicVo.setUserId(getUserId());
            topicVo.setFloor(1);
            TopicInfo topicInfo = topicService.findBy(topicVo);
            if(topicInfo==null){
                return R.success(result);
            }
            topicVo.setFloor(null);
            topicVo.setUserId(null);
        }
        result.put("data",topicService.getTopicReply(page, topicVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(topicService.getTopicReplyOfPage(topicVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    /**
     * 获取指定话题的全部内容
     * @param page
     * @param topicVo
     * @return
     */
    @GetMapping("/getTopicData")
    public R getTopicData(Page page, TopicVo topicVo){
        HashMap<String,Object> result=new HashMap<>();

        if(topicVo.isShow() || !(isLogin() && getCurrentUser().getUser().getRole().equals(DictionaryCode.USER_ROLE_1))){
            topicVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        }
        List<TopicInfo> topicInfoList = topicService.getTopicData(page, topicVo,false); // 仅所有楼层
        result.put("data",topicInfoList);
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(topicService.getTopicDataOfPage(topicVo,true)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    /**
     * 发表话题
     * @return
     */
    @PostMapping("/saveTopic")
    public R saveTopic(@RequestParam("topicVoStr")  String topicVoStr,@RequestParam(value = "imageFiles",required = false) MultipartFile[] imageFiles, HttpServletRequest request){
        // 解析
        ObjectMapper objectMapper = new ObjectMapper();
        TopicVo topicVo =objectMapper.convertValue(JSONObject.parse(topicVoStr),TopicVo.class);

        String ip = IpUtils.getIpAddress(request);
        topicVo.setIp(ip);
        topicVo.setAddress(ibsService.getAddress(ip).get("address"));

        if(imageFiles!=null){
            StringBuffer images = new StringBuffer();
            // 将解析整理资源并存储,并返回资源信息
            ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(
                    ResourcesPathUtils.HYLD,
                    ResourcesPathUtils.getPhotoDirFile(ResourcesPathUtils.HYLD),
                    ResourcesPathUtils.getVideoDirFile(ResourcesPathUtils.HYLD),
                    ResourcesPathUtils.getAudioDirFile(ResourcesPathUtils.HYLD),
                    ResourcesPathUtils.getFileDirFile(ResourcesPathUtils.HYLD));
            for(int i=0;i<imageFiles.length;i++){
                String imageNewName = resourceUploadAndDownload.resourceUpload(imageFiles[i],getUserId()).get("newName");
                images.append(imageNewName+";");
            }
            topicVo.setImages(String.valueOf(images));
        }


        topicVo.setBelongToFloor(1);
        topicVo.setParentId(-1);
        topicVo.setFloor(1);
        topicVo.setUserId(getUserId());
        topicVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        topicService.add(topicVo);
        return R.success("话题发表成功!");
    }

    /**
     * 保存回复话题
     * @return
     */
    @PreAuthorize("hasAuthority('replyMe_reply')")
    @PostMapping("/saveReplyTopicInfo")
    public R saveReplyTopicInfo(@RequestParam("replyTopicVoStr") String replyTopicVoStr,@RequestParam(value = "imageFiles",required = false) MultipartFile[] imageFiles, HttpServletRequest request){
        // 解析
        ObjectMapper objectMapper = new ObjectMapper();
        TopicVo topicVo =objectMapper.convertValue(JSONObject.parse(replyTopicVoStr),TopicVo.class);
        String ip = IpUtils.getIpAddress(request);
        topicVo.setIp(ip);
        topicVo.setAddress(ibsService.getAddress(ip).get("address"));

        if(imageFiles!=null){
            StringBuffer images = new StringBuffer();
            // 将解析整理资源并存储,并返回资源信息
            ResourceUploadAndDownloadUtils resourceUploadAndDownload=new ResourceUploadAndDownloadUtils(
                    ResourcesPathUtils.HYLD,
                    ResourcesPathUtils.getPhotoDirFile(ResourcesPathUtils.HYLD),
                    ResourcesPathUtils.getVideoDirFile(ResourcesPathUtils.HYLD),
                    ResourcesPathUtils.getAudioDirFile(ResourcesPathUtils.HYLD),
                    ResourcesPathUtils.getFileDirFile(ResourcesPathUtils.HYLD));
            for(int i=0;i<imageFiles.length;i++){
                String imageNewName = resourceUploadAndDownload.resourceUpload(imageFiles[i],getUserId()).get("newName");
                images.append(imageNewName+";");
            }
            topicVo.setImages(String.valueOf(images));
        }





        if(topicVo.isReplyTopic()){
            topicVo.setFloor(topicService.getTopicData(null,topicVo,true).size()+1); // 放在开头,所有状态的回复都统计
            topicVo.setBelongToFloor(topicVo.getFloor());
        }
        topicVo.setUserId(getUserId());
        topicVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        topicService.saveReplyTopicInfo(topicVo);
        return R.success("话题回复成功!");
    }

    /**
     * 删除话题
     * @param topicVo
     * @return
     */
    @PreAuthorize("hasAuthority('myTopic_delete')")
    @PostMapping("/deleteTopic")
    public R deleteTopic(@RequestBody TopicVo topicVo){
        topicVo.setStatus(DictionaryCode.SPEECH_STATUS_3);
        topicService.updateStatus(topicVo);
        return R.success("话题删除成功!");
    }

    /**
     * 冻结话题
     * @param topicVo
     * @return
     */
    @PreAuthorize("hasAuthority('topicManage_frozenTopic')")
    @PostMapping("/frozenTopic")
    public R frozenTopic(@RequestBody TopicVo topicVo){
        topicVo.setStatus(DictionaryCode.SPEECH_STATUS_2);
        topicService.updateStatus(topicVo);
        return R.success("话题/回复已冻结!");
    }

    /**
     * 恢复话题
     * @param topicVo
     * @return
     */
    @PreAuthorize("hasAuthority('topicManage_returnTopic')")
    @PostMapping("/returnTopic")
    public R returnTopic(@RequestBody TopicVo topicVo){
        topicVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        topicService.updateStatus(topicVo);
        return R.success("话题/回复已恢复!");
    }

    /**
     * 查询所有回复我的
     * @return
     */
    @GetMapping("/searchReplyMe")
    public R searchReplyMe(Page page, TopicVo topicVo){
        HashMap<String,Object> result=new HashMap<>();
        topicVo.setUserId(getUserId());
        topicVo.setStatus(DictionaryCode.SPEECH_STATUS_1);

        List<TopicInfo> topicInfoList = topicService.searchReplyMe(page, topicVo); // 仅所有楼层
        result.put("data",topicInfoList);
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(topicService.searchReplyMeOfPage(topicVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }
}
