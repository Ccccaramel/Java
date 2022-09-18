package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Menu;
import com.ding.hyld.info.MenuInfo;
import com.ding.hyld.vo.MenuVo;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<MenuInfo> getAllMenu();
    void addMenu(MenuVo menuVo);
    void updateMenu(MenuVo menuVo);

    List<MenuInfo> getRoleMenuTree(Integer roleId);

    List<String> getCurrentUserPower(Integer roleId);
}
