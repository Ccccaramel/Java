package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.OfficialVersionUpdateLog;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.info.OfficialVersionUpdateLogInfo;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.mapper.OfficialVersionUpdateLogMapper;
import com.ding.hyld.mapper.UpdateLogMapper;
import com.ding.hyld.service.OfficialVersionUpdateLogService;
import com.ding.hyld.service.UpdateLogService;
import com.ding.hyld.vo.OfficialVersionUpdateLogVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UpdateLogVo;
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
