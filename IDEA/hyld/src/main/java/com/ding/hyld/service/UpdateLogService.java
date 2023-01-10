package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UpdateLogVo;

import java.util.List;

public interface UpdateLogService extends IService<UpdateLog> {
    List<UpdateLogInfo> searchUpdateLog(Page page, UpdateLogVo updateLogVo);

    void update(UpdateLogVo updateLogVo);

    void add(UpdateLogVo updateLogVo);

    Integer searchUpdateLogOfPage(UpdateLogVo updateLogVo);
}
