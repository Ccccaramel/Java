package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.UpdateLog;
import com.ding.office.info.UpdateLogInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.UpdateLogVo;

import java.util.List;

public interface UpdateLogService extends IService<UpdateLog> {
    List<UpdateLogInfo> searchUpdateLog(Page page, UpdateLogVo updateLogVo);

    void update(UpdateLogVo updateLogVo);

    void add(UpdateLogVo updateLogVo);

    Integer searchUpdateLogOfPage(UpdateLogVo updateLogVo);
}
