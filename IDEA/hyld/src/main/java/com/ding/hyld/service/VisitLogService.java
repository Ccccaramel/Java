package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.entity.VisitLog;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.info.VisitLogInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UpdateLogVo;
import com.ding.hyld.vo.VisitLogVo;

import java.util.List;

public interface VisitLogService extends IService<VisitLog> {
    List<VisitLogInfo> searchVisitLog(Page page, VisitLogVo visitLogVo);

    void add(VisitLogVo visitLogVo);

    Integer searchVisitLogOfPage(VisitLogVo visitLogVo);
}
