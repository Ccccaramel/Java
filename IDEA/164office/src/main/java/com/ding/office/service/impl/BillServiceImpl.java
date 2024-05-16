package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.Bill;
import com.ding.office.info.BillInfo;
import com.ding.office.info.BillStatisticsInfo;
import com.ding.office.mapper.BillMapper;
import com.ding.office.service.BillService;
import com.ding.office.vo.BillVo;
import com.ding.office.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill> implements BillService {


    @Override
    public List<BillInfo> search(Page page, BillVo billVo) {
        return baseMapper.search(page,billVo);
    }

    @Override
    public Integer searchPage(BillVo billVo) {
        return baseMapper.searchPage(billVo);
    }

    @Override
    public void add(BillVo billVo) {
        baseMapper.add(billVo);
    }

    @Override
    public void update(BillVo billVo) {
        baseMapper.update(billVo);
    }

    @Override
    public void delete(BillVo billVo) {
        baseMapper.delete(billVo);
    }

    @Override
    public List<BillStatisticsInfo> getBillStatistics(BillVo billVo) {
        return baseMapper.getBillStatistics(billVo);
    }
}
