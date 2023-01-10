package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.SystemConfig;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.info.SystemConfigInfo;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.SystemConfigVo;
import com.ding.hyld.vo.UpdateLogVo;

import java.util.List;

public interface SystemConfigService extends IService<SystemConfig> {
    List<SystemConfigInfo> searchSystemConfig(Page page, SystemConfigVo systemConfigVo);

    void update(SystemConfigVo systemConfigVo);

    void add(SystemConfigVo systemConfigVo);

    SystemConfigInfo findByKey(String key);

    Integer searchSystemConfigOfPage(SystemConfigVo systemConfigVo);
}
