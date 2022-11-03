package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.entity.VisitLog;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.info.VisitLogInfo;
import com.ding.hyld.mapper.UpdateLogMapper;
import com.ding.hyld.mapper.VisitLogMapper;
import com.ding.hyld.service.UpdateLogService;
import com.ding.hyld.service.VisitLogService;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UpdateLogVo;
import com.ding.hyld.vo.VisitLogVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitLogServiceImpl extends ServiceImpl<VisitLogMapper, VisitLog> implements VisitLogService {
    @Override
    public List<VisitLogInfo> searchVisitLog(Page page, VisitLogVo visitLogVo) {
        return baseMapper.searchVisitLog(page, visitLogVo);
    }

    @Override
    public void add(VisitLogVo visitLogVo) {
        baseMapper.add(visitLogVo);
    }
}
