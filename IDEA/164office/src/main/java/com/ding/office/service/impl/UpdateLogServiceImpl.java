package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.UpdateLog;
import com.ding.office.info.UpdateLogInfo;
import com.ding.office.mapper.UpdateLogMapper;
import com.ding.office.service.UpdateLogService;
import com.ding.office.vo.Page;
import com.ding.office.vo.UpdateLogVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateLogServiceImpl extends ServiceImpl<UpdateLogMapper, UpdateLog> implements UpdateLogService {
    @Override
    public List<UpdateLogInfo> searchUpdateLog(Page page, UpdateLogVo updateLogVo) {
        return baseMapper.searchUpdateLog(page, updateLogVo);
    }

    @Override
    public void update(UpdateLogVo updateLogVo) {
        baseMapper.update(updateLogVo);
    }

    @Override
    public void add(UpdateLogVo updateLogVo) {
        baseMapper.add(updateLogVo);
    }

    @Override
    public Integer searchUpdateLogOfPage(UpdateLogVo updateLogVo) {
        return baseMapper.searchUpdateLogOfPage(updateLogVo);
    }
}
