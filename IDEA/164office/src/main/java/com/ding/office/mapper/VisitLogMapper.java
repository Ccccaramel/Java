package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.VisitLog;
import com.ding.office.info.VisitLogInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.VisitLogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VisitLogMapper extends BaseMapper<VisitLog> {
    List<VisitLogInfo> searchVisitLog(@Param("page") Page page, @Param("visitLogVo") VisitLogVo visitLogVo);

    void add(@Param("visitLogVo") VisitLogVo visitLogVo);

    Integer searchVisitLogOfPage(@Param("visitLogVo") VisitLogVo visitLogVo);
}
