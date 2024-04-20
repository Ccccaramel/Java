package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.BlogFile;
import com.ding.hyld.entity.ChatFile;
import com.ding.hyld.info.BlogFileInfo;
import com.ding.hyld.info.ChatFileInfo;
import com.ding.hyld.vo.BlogFileVo;
import com.ding.hyld.vo.ChatFileVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatFileMapper extends BaseMapper<ChatFile> {
    void add(@Param("chatFileVo") ChatFileVo chatFileVo);

    List<ChatFileInfo> searchChatFile(@Param("chatFileVo") ChatFileVo chatFileVo);
}
