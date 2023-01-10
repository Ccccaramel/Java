package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.entity.Player;
import com.ding.hyld.entity.UserWithPlayer;
import com.ding.hyld.mapper.PlayerMapper;
import com.ding.hyld.service.PlayerService;
import com.ding.hyld.service.UserWithPlayerService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.PlayerVo;
import com.ding.hyld.vo.TeamMemberVo;
import com.ding.hyld.vo.UserWithPlayerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ding.hyld.info.PlayerInfo;

import java.util.List;
import java.util.Objects;

@Service
public class PlayerServiceImpl extends ServiceImpl<PlayerMapper, Player> implements PlayerService {
    @Autowired
    private UserWithPlayerService userWithPlayerService;

    @Override
    public List<PlayerInfo> searchPlayerInfo(Page page, PlayerVo playerVo) {
        return baseMapper.searchPlayerInfo(page, playerVo);
    }

    @Override
    public Integer addPlayer(PlayerVo playerVo) {
        PlayerInfo playerInfo=baseMapper.findBy(playerVo);
        if(playerInfo==null){
            baseMapper.addPlayer(playerVo);
            return playerVo.getId();
        }
        return playerInfo.getId();
    }

    /**
     * 用户新增关联游戏账号
     * @param playerVo
     * @return
     */
    @Override
    public Integer userRelationPlayer(PlayerVo playerVo) {
        PlayerInfo playerInfo=baseMapper.findBy(playerVo);
        if(playerInfo==null){ // 游戏账号不存在,新增
            baseMapper.addPlayer(playerVo);
            return playerVo.getId();
        }
        else{ // 游戏账号已存在
            return playerInfo.getId();
        }
    }

    @Override
    public void updateRelationPlayer(PlayerVo playerVo) {
        baseMapper.update(playerVo);
    }

    @Override
    public void updatePlayer(PlayerVo playerVo) {
        baseMapper.updatePlayer(playerVo);
    }

    @Override
    public PlayerInfo findBy(PlayerVo playerVo) {
        return baseMapper.findBy(playerVo);
    }

    @Override
    public Integer searchPlayerInfoOfPage(PlayerVo playerVo) {
        return baseMapper.searchPlayerInfoOfPage(playerVo);
    }
}
