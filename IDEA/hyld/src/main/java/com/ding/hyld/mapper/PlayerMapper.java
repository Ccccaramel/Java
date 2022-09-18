package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Player;
import com.ding.hyld.entity.UserWithPlayer;
import com.ding.hyld.info.PlayerInfo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.PlayerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlayerMapper extends BaseMapper<Player> {

    Integer addPlayer(@Param("playerVo")PlayerVo playerVo);

    /**
     * 仅根据 scid 查询
     */
    Player findBy(@Param("playerVo")PlayerVo playerVo);

    PlayerInfo findById(Integer id);

    List<PlayerInfo> searchPlayerInfo(@Param("page")Page page, @Param("playerVo") PlayerVo playerVo);

    void update(@Param("playerVo") PlayerVo playerVo);
}
