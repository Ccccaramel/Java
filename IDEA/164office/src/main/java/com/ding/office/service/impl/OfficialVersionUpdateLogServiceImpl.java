package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.OfficialVersionUpdateLog;
import com.ding.office.info.OfficialVersionUpdateLogInfo;
import com.ding.office.mapper.OfficialVersionUpdateLogMapper;
import com.ding.office.service.OfficialVersionUpdateLogService;
import com.ding.office.vo.OfficialVersionUpdateLogVo;
import com.ding.office.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficialVersionUpdateLogServiceImpl extends ServiceImpl<OfficialVersionUpdateLogMapper, OfficialVersionUpdateLog> implements OfficialVersionUpdateLogService {
    @Override
    public List<OfficialVersionUpdateLogInfo> searchOfficialVersionUpdateLog(Page page, OfficialVersionUpdateLogVo officialVersionUpdateLogVo) {
        return baseMapper.searchOfficialVersionUpdateLog(page, officialVersionUpdateLogVo);
    }

    @Override
    public void update(OfficialVersionUpdateLogVo officialVersionUpdateLogVo) {
        baseMapper.update(officialVersionUpdateLogVo);
    }

    @Override
    public void add(OfficialVersionUpdateLogVo officialVersionUpdateLogVo) {
        baseMapper.add(officialVersionUpdateLogVo);
    }

    @Override
    public void delete(OfficialVersionUpdateLogVo officialVersionUpdateLogVo) {
        baseMapper.delete(officialVersionUpdateLogVo);
    }

    @Override
    public Integer searchOfficialVersionUpdateLogOfPage(OfficialVersionUpdateLogVo officialVersionUpdateLogVo) {
        return baseMapper.searchOfficialVersionUpdateLogOfPage(officialVersionUpdateLogVo);
    }
}
