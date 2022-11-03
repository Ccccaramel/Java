package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.HeadPortrait;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.info.HeadPortraitInfo;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.vo.HeadPortraitVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UpdateLogVo;

import java.util.List;

public interface HeadPortraitService extends IService<HeadPortrait> {
    List<HeadPortraitInfo> searchHeadPortrait(Page page, HeadPortraitVo headPortraitVo);

    void update(HeadPortraitVo headPortraitVo);

    void add(HeadPortraitVo headPortraitVo);

    void del(HeadPortraitVo headPortraitVo);
}
