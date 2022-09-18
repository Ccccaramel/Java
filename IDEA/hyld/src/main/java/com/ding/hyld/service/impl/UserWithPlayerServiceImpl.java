package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.UserWithPlayer;
import com.ding.hyld.info.UserWithPlayerInfo;
import com.ding.hyld.mapper.UserWithPlayerMapper;
import com.ding.hyld.service.UserWithPlayerService;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UserWithPlayerVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserWithPlayerServiceImpl extends ServiceImpl<UserWithPlayerMapper,UserWithPlayer> implements UserWithPlayerService {
    @Override
    public UserWithPlayer findBy(UserWithPlayerVo userWithPlayerVo) {
        return baseMapper.findBy(userWithPlayerVo);
    }

    @Override
    public void add(UserWithPlayerVo userWithPlayerVo) {
        baseMapper.add(userWithPlayerVo);
    }

    @Override
    public List<UserWithPlayerInfo> searchRelation(UserWithPlayerVo userWithPlayerVo, Page page) {
        return baseMapper.searchRelation(userWithPlayerVo, page);
    }

    @Override
    public void changeRelationStatus(Integer playerId, Integer userId, Integer relationStatus) {
        baseMapper.changeRelationStatus(playerId, userId, relationStatus);
    }

    @Override
    public void saveCheckInfo(Integer relationId, String playerMainPageNewName, String playerPreparePageNewName, Integer checkStatus) {
        baseMapper.saveCheckInfo(relationId, playerMainPageNewName, playerPreparePageNewName, checkStatus);
    }

    @Override
    public void updateCheckInfo(UserWithPlayerVo userWithPlayerVo) {
        baseMapper.updateCheckInfo(userWithPlayerVo);
    }

}
