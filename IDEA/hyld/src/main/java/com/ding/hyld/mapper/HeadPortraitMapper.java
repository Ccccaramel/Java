package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.HeadPortrait;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.info.HeadPortraitInfo;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.vo.HeadPortraitVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UpdateLogVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HeadPortraitMapper extends BaseMapper<HeadPortrait> {
    List<HeadPortraitInfo> searchHeadPortrait(@Param("page") Page page, @Param("headPortraitVo") HeadPortraitVo headPortraitVo);

    void update(@Param("headPortraitVo") HeadPortraitVo headPortraitVo);

    void add(@Param("headPortraitVo") HeadPortraitVo headPortraitVo);

    HeadPortraitInfo findById(Integer id);

    void del(@Param("headPortraitVo") HeadPortraitVo headPortraitVo);
}
