package com.ding.office.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.office.entity.GameRole;
import com.ding.office.info.GameRoleInfo;
import com.ding.office.mapper.GameRoleMapper;
import com.ding.office.service.GameRoleService;
import com.ding.office.vo.GameRoleVo;
import com.ding.office.vo.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameRoleServiceImpl extends ServiceImpl<GameRoleMapper, GameRole> implements GameRoleService {
    @Override
    public List<GameRoleInfo> searchGameRole(Page page, GameRoleVo gameRoleVo) {
        return baseMapper.searchGameRole(page, gameRoleVo);
    }

    @Override
    public void update(GameRoleVo gameRoleVo) {
        baseMapper.update(gameRoleVo);
    }

    @Override
    public void add(GameRoleVo gameRoleVo) {
        baseMapper.add(gameRoleVo);
    }

    @Override
    public List<GameRoleInfo> getAllGameRole() {
        return baseMapper.getAllGameRole();
    }

    @Override
    public List<GameRoleInfo> getBasicForm(GameRoleVo gameRoleVo) {
        return baseMapper.getBasicForm(gameRoleVo);
    }

    @Override
    public GameRoleInfo searchGameRoleInfoById(GameRoleVo gameRoleVo) {
        return baseMapper.searchGameRoleInfoById(gameRoleVo);
    }

    @Override
    public Integer searchGameRoleOfPage(GameRoleVo gameRoleVo) {
        return baseMapper.searchGameRoleOfPage(gameRoleVo);
    }
}
