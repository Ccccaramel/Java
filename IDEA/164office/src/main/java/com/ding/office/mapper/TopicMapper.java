package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.Topic;
import com.ding.office.info.TopicInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.TopicVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TopicMapper extends BaseMapper<Topic> {
    List<TopicInfo> searchTopic(@Param("page") Page page, @Param("topicVo") TopicVo topicVo);

    void update(@Param("topicVo") TopicVo topicVo);

    void add(@Param("topicVo") TopicVo topicVo);

    List<TopicInfo> getTopicData(@Param("page") Page page, @Param("topicVo") TopicVo topicVo, @Param("onlyFloor") boolean onlyFloor);

    void saveReplyTopicInfo(@Param("topicVo") TopicVo topicVo);

    TopicInfo findBy(@Param("topicVo") TopicVo topicVo);

    TopicInfo findById(@Param("topicId") Integer topicId);

    void updateStatus(@Param("topicVo") TopicVo topicVo);

    List<TopicInfo> getTopicReply(@Param("page") Page page, @Param("topicVo") TopicVo topicVo);

    List<TopicInfo> searchReplyMe(@Param("page") Page page, @Param("topicVo") TopicVo topicVo);

    Integer searchTopicOfPage(@Param("topicVo") TopicVo topicVo);

    Integer getTopicReplyOfPage(@Param("topicVo") TopicVo topicVo);

    Integer getTopicDataOfPage(@Param("topicVo") TopicVo topicVo, @Param("onlyFloor") boolean onlyFloor);

    Integer searchReplyMeOfPage(@Param("topicVo") TopicVo topicVo);
}
