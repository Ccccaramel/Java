package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.StarPower;
import com.ding.office.info.StarPowerInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.StarPowerVo;

import java.util.List;

public interface StarPowerService extends IService<StarPower> {
    List<StarPowerInfo> searchStarPower(Page page, StarPowerVo starPowerVo);

    void update(StarPowerVo starPowerVo);

    void add(StarPowerVo starPowerVo);

    Integer searchStarPowerOfPage(StarPowerVo starPowerVo);
}
