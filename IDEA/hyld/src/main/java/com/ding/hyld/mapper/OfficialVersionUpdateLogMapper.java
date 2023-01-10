package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.OfficialVersionUpdateLog;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.info.OfficialVersionUpdateLogInfo;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.vo.OfficialVersionUpdateLogVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UpdateLogVo;
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
