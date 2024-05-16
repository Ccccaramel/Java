package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.Bill;
import com.ding.office.info.BillInfo;
import com.ding.office.info.BillStatisticsInfo;
import com.ding.office.vo.BillVo;
import com.ding.office.vo.Page;

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
