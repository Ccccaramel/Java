package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.SystemConfig;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.info.SystemConfigInfo;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.mapper.SystemConfigMapper;
import com.ding.hyld.mapper.UpdateLogMapper;
import com.ding.hyld.service.SystemConfigService;
import com.ding.hyld.service.UpdateLogService;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.SystemConfigVo;
import com.ding.hyld.vo.UpdateLogVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemConfigServiceImpl extends ServiceImpl<SystemConfigMapper, SystemConfig> implements SystemConfigService {
    @Override
    public List<SystemConfigInfo> searchSystemConfig(Page page, SystemConfigVo systemConfigVo) {
        return baseMapper.searchSystemConfig(page, systemConfigVo);
    }

    @Override
    public void update(SystemConfigVo systemConfigVo) {
        baseMapper.update(systemConfigVo);
    }

    @Override
    public void add(SystemConfigVo systemConfigVo) {
        baseMapper.add(systemConfigVo);
    }

    @Override
    public SystemConfigInfo findByKey(String key) {
        return baseMapper.findByKey(key);
    }

    @Override
    public Integer searchSystemConfigOfPage(SystemConfigVo systemConfigVo) {
        return baseMapper.searchSystemConfigOfPage(systemConfigVo);
    }
}
