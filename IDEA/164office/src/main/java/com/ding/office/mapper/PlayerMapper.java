package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.Player;
import com.ding.office.info.PlayerInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.PlayerVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PlayerMapper extends BaseMapper<Player> {

    void addPlayer(@Param("playerVo") PlayerVo playerVo);

    /**
     * 仅根据 scid 查询
     */
    PlayerInfo findBy(@Param("playerVo") PlayerVo playerVo);

    PlayerInfo findById(Integer id);

    List<PlayerInfo> searchPlayerInfo(@Param("page")Page page, @Param("playerVo") PlayerVo playerVo);

    /**
     * 用户更改游戏账号信息
     * @param playerVo
     */
    void update(@Param("playerVo") PlayerVo playerVo);

    /**
     * 管理员更改游戏账号信息
     * @param playerVo
     */
    void updatePlayer(@Param("playerVo") PlayerVo playerVo);

    Integer searchPlayerInfoOfPage(@Param("playerVo") PlayerVo playerVo);
}
