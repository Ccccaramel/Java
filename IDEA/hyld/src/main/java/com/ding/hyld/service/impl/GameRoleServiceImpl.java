package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.GameRole;
import com.ding.hyld.entity.UpdateLog;
import com.ding.hyld.info.GameRoleInfo;
import com.ding.hyld.info.UpdateLogInfo;
import com.ding.hyld.mapper.GameRoleMapper;
import com.ding.hyld.mapper.UpdateLogMapper;
import com.ding.hyld.service.GameRoleService;
import com.ding.hyld.service.UpdateLogService;
import com.ding.hyld.vo.GameRoleVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UpdateLogVo;
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
}
