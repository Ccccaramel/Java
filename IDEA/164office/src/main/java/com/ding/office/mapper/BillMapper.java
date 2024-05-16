package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.Bill;
import com.ding.office.info.BillInfo;
import com.ding.office.info.BillStatisticsInfo;
import com.ding.office.vo.BillVo;
import com.ding.office.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BillMapper extends BaseMapper<Bill> {
    List<BillInfo> search(@Param("page") Page page, @Param("billVo") BillVo billVo);

    void add(@Param("billVo") BillVo billVo);

    void update(@Param("billVo") BillVo billVo);

    void delete(@Param("billVo") BillVo billVo);

    Integer searchPage(@Param("billVo") BillVo billVo);

    List<BillStatisticsInfo> getBillStatistics(@Param("billVo") BillVo billVo);
}
