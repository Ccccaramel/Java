package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.ChatFile;
import com.ding.office.info.ChatFileInfo;
import com.ding.office.vo.ChatFileVo;
import java.util.List;

public interface ChatFileService extends IService<ChatFile> {
    void add(ChatFileVo chatFileVo);

    List<ChatFileInfo> searchChatFile(ChatFileVo chatFileVo);
}
