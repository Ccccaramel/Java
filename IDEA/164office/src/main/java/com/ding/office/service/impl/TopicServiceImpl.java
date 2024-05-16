package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.Topic;
import com.ding.office.info.TopicInfo;
import com.ding.office.mapper.TopicMapper;
import com.ding.office.service.TopicService;
import com.ding.office.service.UserService;
import com.ding.office.utils.Tree;
import com.ding.office.utils.TreeUtils;
import com.ding.office.vo.Page;
import com.ding.office.vo.TopicVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, Topic> implements TopicService {
    @Autowired
    UserService userService;

    @Override
    public List<TopicInfo> searchTopic(Page page, TopicVo topicVo) {
        return baseMapper.searchTopic(page, topicVo);
    }

    @Override
    public void update(TopicVo topicVo) {
        baseMapper.update(topicVo);
    }

    @Override
    public void add(TopicVo topicVo) {
        baseMapper.add(topicVo);
    }

    /**
     * 话题分页数据
     *
     * 有一个问题
     *   当不是第一页的时候, TreeUtils.transformation(topicInfoList) 内会出现"群龙无首"的问题从而报错
     *   于是我只能先将"头"添加进去,再"砍掉"
     * @param page
     * @param topicVo
     * @param onlyFloor
     * @return
     */
    @Override
    public List<TopicInfo> getTopicData(Page page, TopicVo topicVo, boolean onlyFloor) {
        List<TopicInfo> topicInfoList = baseMapper.getTopicData(page, topicVo, onlyFloor);
        if(page!=null && page.getCurrentPage()!=1){
            topicInfoList.add(baseMapper.findById(topicVo.getId()));
        }
        if(!onlyFloor && topicInfoList!=null){
            topicInfoList = TreeUtils.transformation(topicInfoList,-1);
            if(topicInfoList.get(0).getChildren()!=null){
                for (Tree topicInfo:topicInfoList.get(0).getChildren()) {
                    topicInfoList.add((TopicInfo)topicInfo);
                    List<Tree> children = topicInfo.getChildren();
                    if(children!=null){
                        List<Tree> treeList = TreeUtils.toList(topicInfo.getChildren());
                        Collections.sort(treeList);
                        topicInfo.setChildren(treeList);
                    }
                }
            }

            topicInfoList.get(0).setChildren(null); // 把第一楼的 children 清空
        }
        return topicInfoList;
    }



    @Override
    public void saveReplyTopicInfo(TopicVo topicVo) {
        TopicInfo topicInfo = baseMapper.findById(topicVo.getParentId());
        if(!topicInfo.getUserInfo().getId().equals(topicVo.getUserId())){
            userService.addEx(topicVo.getUserId(),1); // 回复
            userService.addEx(topicInfo.getUserInfo().getId(),2); // 被回复
        }
        baseMapper.saveReplyTopicInfo(topicVo);
    }

    @Override
    public TopicInfo findBy(TopicVo topicVo) {
        return baseMapper.findBy(topicVo);
    }

    @Override
    public TopicInfo findById(Integer topicId) {
        return baseMapper.findById(topicId);
    }

    @Override
    public List<TopicInfo> getAllReplyData(List<TopicInfo> topicInfoList) {
        for(TopicInfo topicInfo : topicInfoList){

        }
        return topicInfoList;
    }

    @Override
    public void updateStatus(TopicVo topicVo) {
        baseMapper.updateStatus(topicVo);
    }

    @Override
    public List<TopicInfo> getTopicReply(Page page, TopicVo topicVo) {
        return baseMapper.getTopicReply(page,topicVo);
    }

    @Override
    public List<TopicInfo> searchReplyMe(Page page, TopicVo topicVo) {
        return baseMapper.searchReplyMe(page,topicVo);
    }

    @Override
    public Integer searchTopicOfPage(TopicVo topicVo) {
        return baseMapper.searchTopicOfPage(topicVo);
    }

    @Override
    public Integer getTopicReplyOfPage(TopicVo topicVo) {
        return baseMapper.getTopicReplyOfPage(topicVo);
    }

    @Override
    public Integer getTopicDataOfPage(TopicVo topicVo, boolean onlyFloor) {
        return baseMapper.getTopicDataOfPage(topicVo, onlyFloor);
    }

    @Override
    public Integer searchReplyMeOfPage(TopicVo topicVo) {
        return baseMapper.searchReplyMeOfPage(topicVo);
    }
}
