package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.SystemConfig;
import com.ding.office.info.SystemConfigInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.SystemConfigVo;

import java.util.List;

public interface SystemConfigService extends IService<SystemConfig> {
    List<SystemConfigInfo> searchSystemConfig(Page page, SystemConfigVo systemConfigVo);

    void update(SystemConfigVo systemConfigVo);

    void add(SystemConfigVo systemConfigVo);

    SystemConfigInfo findByKey(String key);

    Integer searchSystemConfigOfPage(SystemConfigVo systemConfigVo);
}
