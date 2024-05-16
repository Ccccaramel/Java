package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.HeadPortrait;
import com.ding.office.info.HeadPortraitInfo;
import com.ding.office.mapper.HeadPortraitMapper;
import com.ding.office.service.HeadPortraitService;
import com.ding.office.vo.HeadPortraitVo;
import com.ding.office.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeadPortraitServiceImpl extends ServiceImpl<HeadPortraitMapper,HeadPortrait> implements HeadPortraitService {
    @Override
    public List<HeadPortraitInfo> searchHeadPortrait(Page page, HeadPortraitVo headPortraitVo) {
        return baseMapper.searchHeadPortrait(page,headPortraitVo);
    }

    @Override
    public void update(HeadPortraitVo headPortraitVo) {
        baseMapper.update(headPortraitVo);
    }

    @Override
    public void add(HeadPortraitVo headPortraitVo) {
        baseMapper.add(headPortraitVo);
    }

    @Override
    public void del(HeadPortraitVo headPortraitVo) {
        baseMapper.del(headPortraitVo);
    }

    @Override
    public Integer searchHeadPortraitOfPage(HeadPortraitVo headPortraitVo) {
        return baseMapper.searchHeadPortraitOfPage(headPortraitVo);
    }
}
