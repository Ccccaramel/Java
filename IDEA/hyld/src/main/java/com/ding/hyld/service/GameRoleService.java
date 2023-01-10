package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.GameRole;
import com.ding.hyld.info.GameRoleInfo;
import com.ding.hyld.vo.GameRoleVo;
import com.ding.hyld.vo.Page;

import java.util.List;

public interface GameRoleService extends IService<GameRole> {
    List<GameRoleInfo> searchGameRole(Page page, GameRoleVo gameRoleVo);

    void update(GameRoleVo gameRoleVo);

    void add(GameRoleVo gameRoleVo);

    List<GameRoleInfo> getAllGameRole();

    List<GameRoleInfo> getBasicForm(GameRoleVo gameRoleVo);

    GameRoleInfo searchGameRoleInfoById(GameRoleVo gameRoleVo);

    Integer searchGameRoleOfPage(GameRoleVo gameRoleVo);
}
