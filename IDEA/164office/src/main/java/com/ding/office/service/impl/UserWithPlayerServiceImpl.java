package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.UserWithPlayer;
import com.ding.office.info.UserWithPlayerInfo;
import com.ding.office.mapper.UserWithPlayerMapper;
import com.ding.office.service.UserWithPlayerService;
import com.ding.office.service.UserWithTeamService;
import com.ding.office.vo.Page;
import com.ding.office.vo.UserWithPlayerVo;
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

    @Override
    public Integer searchRelationOfPage(UserWithPlayerVo userWithPlayerVo) {
        return baseMapper.searchRelationOfPage(userWithPlayerVo);
    }

}
