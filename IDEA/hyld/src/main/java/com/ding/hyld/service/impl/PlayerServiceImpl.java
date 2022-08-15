package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Player;
import com.ding.hyld.mapper.PlayerMapper;
import com.ding.hyld.service.PlayerService;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl extends ServiceImpl<PlayerMapper, Player> implements PlayerService {

    @Override
    public Player addPlayer(Player player) {
        Player res=baseMapper.findBy(player);
        if(res==null){
            baseMapper.addPlayer(player);
        }else{
            player = res;
        }
        return player;
    }
}
