package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.GameRolePopularity;
import com.ding.office.info.GameRolePopularityInfo;
import com.ding.office.vo.GameRolePopularityVo;

import java.util.List;

public interface GameRolePopularityService extends IService<GameRolePopularity> {
    void add(GameRolePopularityVo gameRolePopularityVo);

    void delete(GameRolePopularityVo gameRolePopularityVo);

    GameRolePopularityInfo findByGameRoleId(GameRolePopularityVo gameRolePopularityVo);

    List<GameRolePopularityInfo> searchGameRolePopularity(Integer popularityType);
}
