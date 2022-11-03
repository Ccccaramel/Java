package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.GameRolePopularity;
import com.ding.hyld.entity.StarPower;
import com.ding.hyld.info.GameRolePopularityInfo;
import com.ding.hyld.info.StarPowerInfo;
import com.ding.hyld.mapper.GameRolePopularityMapper;
import com.ding.hyld.mapper.StarPowerMapper;
import com.ding.hyld.service.GameRolePopularityService;
import com.ding.hyld.service.StarPowerService;
import com.ding.hyld.vo.GameRolePopularityVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.StarPowerVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameRolePopularityServiceImpl extends ServiceImpl<GameRolePopularityMapper, GameRolePopularity> implements GameRolePopularityService {

    @Override
    public void add(GameRolePopularityVo gameRolePopularityVo) {
        baseMapper.add(gameRolePopularityVo);
    }

    @Override
    public void delete(GameRolePopularityVo gameRolePopularityVo) {
        baseMapper.delete(gameRolePopularityVo);
    }

    @Override
    public GameRolePopularityInfo findByGameRoleId(GameRolePopularityVo gameRolePopularityVo) {
        return baseMapper.findByGameRoleId(gameRolePopularityVo);
    }

    @Override
    public List<GameRolePopularityInfo> searchGameRolePopularity(Integer popularityType) {
        return baseMapper.searchGameRolePopularity(popularityType);
    }
}
