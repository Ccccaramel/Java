package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Gadget;
import com.ding.hyld.entity.StarPower;
import com.ding.hyld.info.GadgetInfo;
import com.ding.hyld.info.StarPowerInfo;
import com.ding.hyld.mapper.GadgetMapper;
import com.ding.hyld.mapper.StarPowerMapper;
import com.ding.hyld.service.GadgetService;
import com.ding.hyld.service.StarPowerService;
import com.ding.hyld.vo.GadgetVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.StarPowerVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GadgetServiceImpl extends ServiceImpl<GadgetMapper, Gadget> implements GadgetService {
    @Override
    public List<GadgetInfo> searchGadget(Page page, GadgetVo gadgetVo) {
        return baseMapper.searchGadget(page, gadgetVo);
    }

    @Override
    public void update(GadgetVo gadgetVo) {
        baseMapper.update(gadgetVo);
    }

    @Override
    public void add(GadgetVo gadgetVo) {
        baseMapper.add(gadgetVo);
    }

    @Override
    public Integer searchGadgetOfPage(GadgetVo gadgetVo) {
        return baseMapper.searchGadgetOfPage(gadgetVo);
    }
}
