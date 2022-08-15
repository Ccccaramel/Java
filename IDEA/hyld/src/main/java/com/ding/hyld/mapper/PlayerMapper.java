package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Player;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PlayerMapper extends BaseMapper<Player> {

    Integer addPlayer(@Param("player")Player player);

    Player findBy(@Param("player")Player player);
}
