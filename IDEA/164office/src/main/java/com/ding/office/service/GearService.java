package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.Gear;
import com.ding.office.info.GearInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.GearVo;

import java.util.List;

public interface GearService extends IService<Gear> {
    List<GearInfo> searchGear(Page page, GearVo gearVo);

    void update(GearVo gearVo);

    void add(GearVo gearVo);

    Integer searchGearOfPage(GearVo gearVo);
}
