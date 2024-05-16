package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.GameRolePopularity;
import com.ding.office.info.GameRolePopularityInfo;
import com.ding.office.vo.GameRolePopularityVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GameRolePopularityMapper extends BaseMapper<GameRolePopularity> {
    void add(@Param("gameRolePopularityVo") GameRolePopularityVo gameRolePopularityVo);

    void delete(@Param("gameRolePopularityVo") GameRolePopularityVo gameRolePopularityVo);

    GameRolePopularityInfo findByGameRoleId(@Param("gameRolePopularityVo") GameRolePopularityVo gameRolePopularityVo);

    List<GameRolePopularityInfo> searchGameRolePopularity(@Param("popularityType")Integer popularityType);
}
