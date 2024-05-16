package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.VisitLog;
import com.ding.office.info.VisitLogInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.VisitLogVo;

import java.util.List;

public interface VisitLogService extends IService<VisitLog> {
    List<VisitLogInfo> searchVisitLog(Page page, VisitLogVo visitLogVo);

    void add(VisitLogVo visitLogVo);

    Integer searchVisitLogOfPage(VisitLogVo visitLogVo);
}
