package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.HeadPortrait;
import com.ding.office.info.HeadPortraitInfo;
import com.ding.office.vo.HeadPortraitVo;
import com.ding.office.vo.Page;
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

    Integer searchHeadPortraitOfPage(@Param("headPortraitVo") HeadPortraitVo headPortraitVo);
}
