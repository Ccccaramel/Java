package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.OfficialVersionUpdateLog;
import com.ding.office.info.OfficialVersionUpdateLogInfo;
import com.ding.office.vo.OfficialVersionUpdateLogVo;
import com.ding.office.vo.Page;

import java.util.List;

public interface OfficialVersionUpdateLogService extends IService<OfficialVersionUpdateLog> {
    List<OfficialVersionUpdateLogInfo> searchOfficialVersionUpdateLog(Page page, OfficialVersionUpdateLogVo officialVersionUpdateLogVo);

    void update(OfficialVersionUpdateLogVo officialVersionUpdateLogVo);

    void add(OfficialVersionUpdateLogVo officialVersionUpdateLogVo);

    void delete(OfficialVersionUpdateLogVo officialVersionUpdateLogVo);

    Integer searchOfficialVersionUpdateLogOfPage(OfficialVersionUpdateLogVo officialVersionUpdateLogVo);
}
