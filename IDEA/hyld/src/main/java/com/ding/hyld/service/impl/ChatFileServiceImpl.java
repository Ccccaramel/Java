package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.ChatFile;
import com.ding.hyld.info.ChatFileInfo;
import com.ding.hyld.mapper.ChatFileMapper;
import com.ding.hyld.service.ChatFileService;
import com.ding.hyld.vo.ChatFileVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatFileServiceImpl extends ServiceImpl<ChatFileMapper, ChatFile> implements ChatFileService {
    @Override
    public void add(ChatFileVo chatFileVo) {
        baseMapper.add(chatFileVo);
    }

    @Override
    public List<ChatFileInfo> searchChatFile(ChatFileVo chatFileVo) {
        return baseMapper.searchChatFile(chatFileVo);
    }

}
