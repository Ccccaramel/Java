package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.ChatFile;
import com.ding.office.info.ChatFileInfo;
import com.ding.office.vo.ChatFileVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatFileMapper extends BaseMapper<ChatFile> {
    void add(@Param("chatFileVo") ChatFileVo chatFileVo);

    List<ChatFileInfo> searchChatFile(@Param("chatFileVo") ChatFileVo chatFileVo);
}
