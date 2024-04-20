package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Chat;
import com.ding.hyld.entity.HeadPortrait;
import com.ding.hyld.info.ChatInfo;
import com.ding.hyld.info.HeadPortraitInfo;
import com.ding.hyld.vo.ChatVo;
import com.ding.hyld.vo.HeadPortraitVo;
import com.ding.hyld.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper extends BaseMapper<Chat> {
    List<ChatInfo> searchChat(@Param("page") Page page, @Param("chatVo") ChatVo chatVo);

    void add(@Param("chatVo") ChatVo chatVo);

    Integer searchChatOfPage(@Param("chatVo") ChatVo chatVo);
}
