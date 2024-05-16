package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.UpdateLog;
import com.ding.office.info.UpdateLogInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.UpdateLogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UpdateLogMapper extends BaseMapper<UpdateLog> {
    List<UpdateLogInfo> searchUpdateLog(@Param("page") Page page, @Param("updateLogVo") UpdateLogVo updateLogVo);

    void update(@Param("updateLogVo") UpdateLogVo updateLogVo);

    void add(@Param("updateLogVo") UpdateLogVo updateLogVo);

    Integer searchUpdateLogOfPage(@Param("updateLogVo") UpdateLogVo updateLogVo);
}
