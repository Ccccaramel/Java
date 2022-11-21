package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Menu;
import com.ding.hyld.info.MenuInfo;
import com.ding.hyld.info.RoleInfo;
import com.ding.hyld.mapper.MenuMapper;
import com.ding.hyld.service.MenuService;
import com.ding.hyld.service.RoleService;
import com.ding.hyld.utils.Tree;
import com.ding.hyld.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Autowired
    private RoleService roleService;

    @Override
    public List<MenuInfo> getAllMenu() {
        return baseMapper.getAllMenu();
    }

    @Override
    public void addMenu(MenuVo menuVo) {
        baseMapper.addMenu(menuVo);
    }

    @Override
    public void updateMenu(MenuVo menuVo) {
        baseMapper.updateMenu(menuVo);
    }

    @Override
    public List<MenuInfo> getRoleMenuTree(Integer roleId) {
        return baseMapper.getRoleMenuTree(roleId);
    }

    @Override
    public List<String> getCurrentUserPower(Integer roleId) {
        RoleInfo roleInfo = roleService.findById(roleId);
        return baseMapper.getCurrentUserPower(roleInfo.getPower().split(","));
    }

    @Override
    public void deleteMenu(MenuVo menuVo) {
        baseMapper.deleteMenu(menuVo);
    }
}
