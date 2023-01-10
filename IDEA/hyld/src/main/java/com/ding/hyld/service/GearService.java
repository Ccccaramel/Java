package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Gear;
import com.ding.hyld.info.GearInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.GearVo;

import java.util.List;

public interface GearService extends IService<Gear> {
    List<GearInfo> searchGear(Page page, GearVo gearVo);

    void update(GearVo gearVo);

    void add(GearVo gearVo);

    Integer searchGearOfPage(GearVo gearVo);
}
