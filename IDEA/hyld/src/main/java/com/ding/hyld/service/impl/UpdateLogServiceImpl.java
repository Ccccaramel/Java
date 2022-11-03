package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.mapper.UpdateLogMapper;
import com.ding.hyld.service.UpdateLogService;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UpdateLogVo;
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
}
