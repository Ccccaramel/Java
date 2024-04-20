package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Chat;
import com.ding.hyld.info.ChatInfo;
import com.ding.hyld.vo.ChatVo;
import com.ding.hyld.vo.Page;

import java.util.List;

public interface ChatService extends IService<Chat> {
    List<ChatInfo> searchChat(Page page, ChatVo chatVo);

    void add(ChatVo chatVo);

    Integer searchChatOfPage(ChatVo chatVo);
}
