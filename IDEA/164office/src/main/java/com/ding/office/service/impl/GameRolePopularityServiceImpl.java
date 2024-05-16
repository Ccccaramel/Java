package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.GameRolePopularity;
import com.ding.office.info.GameRolePopularityInfo;
import com.ding.office.mapper.GameRolePopularityMapper;
import com.ding.office.service.GameRolePopularityService;
import com.ding.office.vo.GameRolePopularityVo;
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
