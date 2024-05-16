package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.HeadPortrait;
import com.ding.office.info.HeadPortraitInfo;
import com.ding.office.vo.HeadPortraitVo;
import com.ding.office.vo.Page;

import java.util.List;

public interface HeadPortraitService extends IService<HeadPortrait> {
    List<HeadPortraitInfo> searchHeadPortrait(Page page, HeadPortraitVo headPortraitVo);

    void update(HeadPortraitVo headPortraitVo);

    void add(HeadPortraitVo headPortraitVo);

    void del(HeadPortraitVo headPortraitVo);

    Integer searchHeadPortraitOfPage(HeadPortraitVo headPortraitVo);
}
