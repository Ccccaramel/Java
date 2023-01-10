package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.StarPower;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.info.StarPowerInfo;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.StarPowerVo;
import com.ding.hyld.vo.UpdateLogVo;

import java.util.List;

public interface StarPowerService extends IService<StarPower> {
    List<StarPowerInfo> searchStarPower(Page page, StarPowerVo starPowerVo);

    void update(StarPowerVo starPowerVo);

    void add(StarPowerVo starPowerVo);

    Integer searchStarPowerOfPage(StarPowerVo starPowerVo);
}
