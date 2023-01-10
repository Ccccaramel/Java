package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Player;
import com.ding.hyld.info.PlayerInfo;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.PlayerVo;
import com.ding.hyld.vo.TeamMemberVo;
import com.ding.hyld.vo.UserWithPlayerVo;

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
