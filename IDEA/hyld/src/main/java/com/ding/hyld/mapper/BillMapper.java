package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Bill;
import com.ding.hyld.info.BillInfo;
import com.ding.hyld.info.BillStatisticsInfo;
import com.ding.hyld.vo.BillVo;
import com.ding.hyld.vo.Page;
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
