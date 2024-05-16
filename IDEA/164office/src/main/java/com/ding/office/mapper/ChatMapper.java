package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.Chat;
import com.ding.office.info.ChatInfo;
import com.ding.office.vo.ChatVo;
import com.ding.office.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper extends BaseMapper<Chat> {
    List<ChatInfo> searchChat(@Param("page") Page page, @Param("chatVo") ChatVo chatVo);

    void add(@Param("chatVo") ChatVo chatVo);

    Integer searchChatOfPage(@Param("chatVo") ChatVo chatVo);
}
