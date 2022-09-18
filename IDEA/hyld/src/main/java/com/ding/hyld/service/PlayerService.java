package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Player;
import com.ding.hyld.info.PlayerInfo;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.PlayerVo;
import com.ding.hyld.vo.TeamMemberVo;

import java.util.List;

public interface PlayerService extends IService<Player> {

    Player addPlayer(TeamMemberVo teamMemberVo);

    List<PlayerInfo> searchPlayerInfo(Page page, PlayerVo playerVo);

    R userRelationPlayer(PlayerVo playerVo, Integer userId);

    R updateRelationPlayer(PlayerVo playerVo, Integer userId);
}
