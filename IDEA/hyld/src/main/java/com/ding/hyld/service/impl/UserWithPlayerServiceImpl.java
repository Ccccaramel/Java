package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.entity.UserWithPlayer;
import com.ding.hyld.entity.UserWithTeam;
import com.ding.hyld.info.UserWithPlayerInfo;
import com.ding.hyld.info.UserWithTeamInfo;
import com.ding.hyld.mapper.UserWithPlayerMapper;
import com.ding.hyld.service.UserWithPlayerService;
import com.ding.hyld.service.UserWithTeamService;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UserWithPlayerVo;
import com.ding.hyld.vo.UserWithTeamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserWithPlayerServiceImpl extends ServiceImpl<UserWithPlayerMapper,UserWithPlayer> implements UserWithPlayerService {
    @Autowired
    private UserWithTeamService userWithTeamService;

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

    @Override
    public UserWithPlayerInfo findById(Integer id) {
        return baseMapper.findById(id);
    }

}
