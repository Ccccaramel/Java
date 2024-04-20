package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Chat;
import com.ding.hyld.info.ChatInfo;
import com.ding.hyld.mapper.ChatMapper;
import com.ding.hyld.service.ChatService;
import com.ding.hyld.vo.ChatVo;
import com.ding.hyld.vo.Page;
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
