package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.Chat;
import com.ding.office.info.ChatInfo;
import com.ding.office.mapper.ChatMapper;
import com.ding.office.service.ChatService;
import com.ding.office.vo.ChatVo;
import com.ding.office.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl extends ServiceImpl<ChatMapper, Chat> implements ChatService {
    @Override
    public List<ChatInfo> searchChat(Page page, ChatVo chatVo) {
        return baseMapper.searchChat(page,chatVo);
    }

    @Override
    public void add(ChatVo chatVo) {
        baseMapper.add(chatVo);
    }

    @Override
    public Integer searchChatOfPage(ChatVo chatVo) {
        return baseMapper.searchChatOfPage(chatVo);
    }
}
