package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.GameRolePopularity;
import com.ding.hyld.entity.StarPower;
import com.ding.hyld.info.GameRolePopularityInfo;
import com.ding.hyld.info.StarPowerInfo;
import com.ding.hyld.vo.GameRolePopularityVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.StarPowerVo;
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
