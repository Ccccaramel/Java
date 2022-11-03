package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.GameRolePopularity;
import com.ding.hyld.entity.StarPower;
import com.ding.hyld.info.GameRolePopularityInfo;
import com.ding.hyld.info.StarPowerInfo;
import com.ding.hyld.vo.GameRolePopularityVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.StarPowerVo;

import java.util.List;

public interface GameRolePopularityService extends IService<GameRolePopularity> {
    void add(GameRolePopularityVo gameRolePopularityVo);

    void delete(GameRolePopularityVo gameRolePopularityVo);

    GameRolePopularityInfo findByGameRoleId(GameRolePopularityVo gameRolePopularityVo);

    List<GameRolePopularityInfo> searchGameRolePopularity(Integer popularityType);
}
