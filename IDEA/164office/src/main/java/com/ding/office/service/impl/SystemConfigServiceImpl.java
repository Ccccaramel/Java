package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.SystemConfig;
import com.ding.office.info.SystemConfigInfo;
import com.ding.office.mapper.SystemConfigMapper;
import com.ding.office.service.SystemConfigService;
import com.ding.office.vo.Page;
import com.ding.office.vo.SystemConfigVo;
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
