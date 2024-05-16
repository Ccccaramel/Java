package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.ChatFile;
import com.ding.office.info.ChatFileInfo;
import com.ding.office.mapper.ChatFileMapper;
import com.ding.office.service.ChatFileService;
import com.ding.office.vo.ChatFileVo;
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
