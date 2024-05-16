package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.OfficialVersionUpdateLog;
import com.ding.office.info.OfficialVersionUpdateLogInfo;
import com.ding.office.vo.OfficialVersionUpdateLogVo;
import com.ding.office.vo.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OfficialVersionUpdateLogMapper extends BaseMapper<OfficialVersionUpdateLog> {
    List<OfficialVersionUpdateLogInfo> searchOfficialVersionUpdateLog(@Param("page") Page page, @Param("officialVersionUpdateLogVo") OfficialVersionUpdateLogVo officialVersionUpdateLogVo);

    void update(@Param("officialVersionUpdateLogVo") OfficialVersionUpdateLogVo officialVersionUpdateLogVo);

    void add(@Param("officialVersionUpdateLogVo") OfficialVersionUpdateLogVo officialVersionUpdateLogVo);

    void delete(@Param("officialVersionUpdateLogVo") OfficialVersionUpdateLogVo officialVersionUpdateLogVo);

    Integer searchOfficialVersionUpdateLogOfPage(@Param("officialVersionUpdateLogVo") OfficialVersionUpdateLogVo officialVersionUpdateLogVo);
}
