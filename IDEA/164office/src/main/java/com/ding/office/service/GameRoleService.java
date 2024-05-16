package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.GameRole;
import com.ding.office.info.GameRoleInfo;
import com.ding.office.vo.GameRoleVo;
import com.ding.office.vo.Page;

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
