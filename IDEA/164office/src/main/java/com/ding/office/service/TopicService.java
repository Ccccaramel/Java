package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.Topic;
import com.ding.office.info.TopicInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.TopicVo;

import java.util.List;

public interface TopicService extends IService<Topic> {
    List<TopicInfo> searchTopic(Page page, TopicVo topicVo);

    void update(TopicVo topicVo);

    void add(TopicVo topicVo);

    /**
     * onlyFloor:
     *   T:仅获取指定话题的所有楼层数据
     *   F:获取指定话题的所有楼层以及楼中楼数据
     * @param page
     * @param topicVo
     * @param onlyFloor
     * @return
     */
    List<TopicInfo> getTopicData(Page page, TopicVo topicVo, boolean onlyFloor);

    void saveReplyTopicInfo(TopicVo topicVo);

    TopicInfo findBy(TopicVo topicVo);

    TopicInfo findById(Integer topicId);

    List<TopicInfo> getAllReplyData(List<TopicInfo> topicInfoList);

    void updateStatus(TopicVo topicVo);

    List<TopicInfo> getTopicReply(Page page, TopicVo topicVo);

    List<TopicInfo> searchReplyMe(Page page, TopicVo topicVo);

    Integer searchTopicOfPage(TopicVo topicVo);

    Integer getTopicReplyOfPage(TopicVo topicVo);

    Integer getTopicDataOfPage(TopicVo topicVo, boolean onlyFloor);

    Integer searchReplyMeOfPage(TopicVo topicVo);
}
