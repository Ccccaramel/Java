package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Bill;
import com.ding.hyld.info.BillInfo;
import com.ding.hyld.info.BillStatisticsInfo;
import com.ding.hyld.mapper.BillMapper;
import com.ding.hyld.service.BillService;
import com.ding.hyld.vo.BillVo;
import com.ding.hyld.vo.Page;
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
