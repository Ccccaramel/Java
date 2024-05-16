package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.Gear;
import com.ding.office.info.GearInfo;
import com.ding.office.mapper.GearMapper;
import com.ding.office.service.GearService;
import com.ding.office.vo.Page;
import com.ding.office.vo.GearVo;
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

    @Override
    public Integer searchGearOfPage(GearVo gearVo) {
        return baseMapper.searchGearOfPage(gearVo);
    }
}
