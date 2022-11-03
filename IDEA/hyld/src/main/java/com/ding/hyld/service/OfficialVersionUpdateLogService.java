package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.OfficialVersionUpdateLog;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.info.OfficialVersionUpdateLogInfo;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.vo.OfficialVersionUpdateLogVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UpdateLogVo;

import java.util.List;

public interface OfficialVersionUpdateLogService extends IService<OfficialVersionUpdateLog> {
    List<OfficialVersionUpdateLogInfo> searchOfficialVersionUpdateLog(Page page, OfficialVersionUpdateLogVo officialVersionUpdateLogVo);

    void update(OfficialVersionUpdateLogVo officialVersionUpdateLogVo);

    void add(OfficialVersionUpdateLogVo officialVersionUpdateLogVo);

    void delete(OfficialVersionUpdateLogVo officialVersionUpdateLogVo);
}
