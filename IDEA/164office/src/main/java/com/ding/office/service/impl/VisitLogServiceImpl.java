package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.VisitLog;
import com.ding.office.info.VisitLogInfo;
import com.ding.office.mapper.VisitLogMapper;
import com.ding.office.service.VisitLogService;
import com.ding.office.vo.Page;
import com.ding.office.vo.VisitLogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class VisitLogServiceImpl extends ServiceImpl<VisitLogMapper, VisitLog> implements VisitLogService {
    @Override
    public List<VisitLogInfo> searchVisitLog(Page page, VisitLogVo visitLogVo) {
        return baseMapper.searchVisitLog(page, visitLogVo);
    }

    @Override
    public void add(VisitLogVo visitLogVo) {
        log.info("visitLogVo:"+visitLogVo.toString());
        baseMapper.add(visitLogVo);
    }

    @Override
    public Integer searchVisitLogOfPage(VisitLogVo visitLogVo) {
        return baseMapper.searchVisitLogOfPage(visitLogVo);
    }
}
