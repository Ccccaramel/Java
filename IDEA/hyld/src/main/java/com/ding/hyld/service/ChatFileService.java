package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.ChatFile;
import com.ding.hyld.info.ChatFileInfo;
import com.ding.hyld.vo.ChatFileVo;
import java.util.List;

public interface ChatFileService extends IService<ChatFile> {
    void add(ChatFileVo chatFileVo);

    List<ChatFileInfo> searchChatFile(ChatFileVo chatFileVo);
}
