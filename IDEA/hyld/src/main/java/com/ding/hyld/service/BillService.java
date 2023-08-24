package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Bill;
import com.ding.hyld.info.BillInfo;
import com.ding.hyld.info.BillStatisticsInfo;
import com.ding.hyld.vo.BillVo;
import com.ding.hyld.vo.Page;

import java.util.List;

public interface BillService extends IService<Bill> {
    List<BillInfo> search(Page page, BillVo billVo);

    Integer searchPage(BillVo billVo);

    void add(BillVo billVo);

    void update(BillVo billVo);

    void delete(BillVo billVo);

    /**
     * 获取账单统计信息
     * @param billVo
     * @return
     */
    List<BillStatisticsInfo> getBillStatistics(BillVo billVo);
}
