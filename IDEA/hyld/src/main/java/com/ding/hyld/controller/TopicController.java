package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.info.TopicInfo;
import com.ding.hyld.security.CurrentUser;
import com.ding.hyld.service.TopicService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.TopicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/topic")
public class TopicController extends BaseController {
    @Autowired
    private TopicService topicService;

    /**
     * 用于搜索话题
     * @param page
     * @param topicVo
     * @return
     */
    @GetMapping("/searchTopic")
    public R searchTopic(Page page, TopicVo topicVo){
        HashMap<String,Object> result=new HashMap<>();
        CurrentUser currentUser = getCurrentUser();
        if(topicVo.isShow() || currentUser==null || currentUser.getUser().getType().equals(DictionaryCode.USER_TYPE_2)){
            topicVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        }
        result.put("data",topicService.searchTopic(page, topicVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(topicService.searchTopic(null,topicVo).size()*1.0/page.getSize()));
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
            result.put("totalPage",Math.ceil(topicService.searchTopic(null,topicVo).size()*1.0/page.getSize()));
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
        if(topicVo.isManageMyTopic() || currentUser==null || currentUser.getUser().getType().equals(DictionaryCode.USER_TYPE_2)){
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
            result.put("totalPage",Math.ceil(topicService.getTopicReply(null,topicVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

    /**
     * 获取制动话题的全部内容
     * @param page
     * @param topicVo
     * @return
     */
    @GetMapping("/getTopicData")
    public R getTopicData(Page page, TopicVo topicVo){
        HashMap<String,Object> result=new HashMap<>();

        CurrentUser currentUser = getCurrentUser();
        if(topicVo.isShow() || currentUser==null || currentUser.getUser().getType().equals(DictionaryCode.USER_TYPE_2)){
            topicVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        }
        List<TopicInfo> topicInfoList = topicService.getTopicData(page, topicVo,false); // 仅所有楼层
        result.put("data",topicInfoList);
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(topicService.getTopicData(null,topicVo,true).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

    /**
     * 发表话题
     * @param topicVo
     * @return
     */
    @PostMapping("/saveTopic")
    public R saveTopic(@RequestBody TopicVo topicVo){
        topicVo.setParentId(-1);
        topicVo.setUserId(getUserId());
        topicVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        topicService.add(topicVo);
        return R.success("话题发表成功!");
    }

    /**
     * 保存回复话题
     * @param topicVo
     * @return
     */
    @PostMapping("/saveReplyTopicInfo")
    public R saveReplyTopicInfo(@RequestBody TopicVo topicVo){
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
    @PostMapping("/frozenTopic")
    public R frozenTopic(@RequestBody TopicVo topicVo){
        topicVo.setStatus(DictionaryCode.SPEECH_STATUS_2);
        topicService.updateStatus(topicVo);
        return R.success("话题已冻结!");
    }

    /**
     * 恢复话题
     * @param topicVo
     * @return
     */
    @PostMapping("/returnTopic")
    public R returnTopic(@RequestBody TopicVo topicVo){
        topicVo.setStatus(DictionaryCode.SPEECH_STATUS_1);
        topicService.updateStatus(topicVo);
        return R.success("话题已恢复!");
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
            result.put("totalPage",Math.ceil(topicService.searchReplyMe(null,topicVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }
}
