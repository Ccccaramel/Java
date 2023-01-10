package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.SystemConfig;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.info.SystemConfigInfo;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.SystemConfigVo;
import com.ding.hyld.vo.UpdateLogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SystemConfigMapper extends BaseMapper<SystemConfig> {
    List<SystemConfigInfo> searchSystemConfig(@Param("page") Page page, @Param("systemConfigVo") SystemConfigVo systemConfigVo);

    void update(@Param("systemConfigVo") SystemConfigVo systemConfigVo);

    void add(@Param("systemConfigVo") SystemConfigVo systemConfigVo);

    SystemConfigInfo findByKey(String key);

    Integer searchSystemConfigOfPage(@Param("systemConfigVo") SystemConfigVo systemConfigVo);
}
