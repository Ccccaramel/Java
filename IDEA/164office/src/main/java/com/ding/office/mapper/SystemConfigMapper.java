package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.SystemConfig;
import com.ding.office.info.SystemConfigInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.SystemConfigVo;
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
