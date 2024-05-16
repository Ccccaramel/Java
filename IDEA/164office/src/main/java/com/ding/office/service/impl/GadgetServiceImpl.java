package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.Gadget;
import com.ding.office.info.GadgetInfo;
import com.ding.office.mapper.GadgetMapper;
import com.ding.office.service.GadgetService;
import com.ding.office.vo.GadgetVo;
import com.ding.office.vo.Page;
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
