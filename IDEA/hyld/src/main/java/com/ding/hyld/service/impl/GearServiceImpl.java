package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Gear;
import com.ding.hyld.info.GearInfo;
import com.ding.hyld.mapper.GearMapper;
import com.ding.hyld.service.GearService;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.GearVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GearServiceImpl extends ServiceImpl<GearMapper, Gear> implements GearService {
    @Override
    public List<GearInfo> searchGear(Page page, GearVo gearVo) {
        return baseMapper.searchGear(page, gearVo);
    }

    @Override
    public void update(GearVo gearVo) {
        baseMapper.update(gearVo);
    }

    @Override
    public void add(GearVo gearVo) {
        baseMapper.add(gearVo);
    }
}
