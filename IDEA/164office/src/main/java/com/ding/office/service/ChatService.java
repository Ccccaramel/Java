package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.Chat;
import com.ding.office.info.ChatInfo;
import com.ding.office.vo.ChatVo;
import com.ding.office.vo.Page;

import java.util.List;

public interface ChatService extends IService<Chat> {
    List<ChatInfo> searchChat(Page page, ChatVo chatVo);

    void add(ChatVo chatVo);

    Integer searchChatOfPage(ChatVo chatVo);
}
