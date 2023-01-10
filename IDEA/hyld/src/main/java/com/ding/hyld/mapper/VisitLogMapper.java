package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.entity.VisitLog;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.info.VisitLogInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UpdateLogVo;
import com.ding.hyld.vo.VisitLogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VisitLogMapper extends BaseMapper<VisitLog> {
    List<VisitLogInfo> searchVisitLog(@Param("page") Page page, @Param("visitLogVo") VisitLogVo visitLogVo);

    void add(@Param("visitLogVo") VisitLogVo visitLogVo);

    Integer searchVisitLogOfPage(@Param("visitLogVo") VisitLogVo visitLogVo);
}
