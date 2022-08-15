package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Player;

public interface PlayerService extends IService<Player> {

    /**
     * 录入一个玩家信息
     *   如果该玩家在数据库中也存在,则直接返回该玩家 id
     *   如果不存在,则 insert 并返回该玩家 id
     * @param player
     * @return
     */
    Player addPlayer(Player player);
}
