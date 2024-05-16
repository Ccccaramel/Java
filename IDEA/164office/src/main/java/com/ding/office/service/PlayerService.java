package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.Player;
import com.ding.office.info.PlayerInfo;
import com.ding.office.vo.Page;
import com.ding.office.vo.PlayerVo;

import java.util.List;

public interface PlayerService extends IService<Player> {

    Integer addPlayer(PlayerVo playerVo);

    List<PlayerInfo> searchPlayerInfo(Page page, PlayerVo playerVo);

    Integer userRelationPlayer(PlayerVo playerVo);

    void updateRelationPlayer(PlayerVo playerVo);

    void updatePlayer(PlayerVo playerVo);

    PlayerInfo findBy(PlayerVo playerVo);

    Integer searchPlayerInfoOfPage(PlayerVo playerVo);
}
