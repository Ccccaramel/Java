package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.Menu;
import com.ding.office.info.MenuInfo;
import com.ding.office.vo.MenuVo;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<MenuInfo> getAllMenu();
    void addMenu(MenuVo menuVo);
    void updateMenu(MenuVo menuVo);

    List<MenuInfo> getRoleMenuTree(Integer roleId);

    List<String> getCurrentUserPower(Integer roleId);

    void deleteMenu(MenuVo menuVo);
}
