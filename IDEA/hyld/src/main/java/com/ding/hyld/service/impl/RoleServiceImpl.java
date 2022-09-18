package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.Role;
import com.ding.hyld.info.MenuInfo;
import com.ding.hyld.info.RoleInfo;
import com.ding.hyld.mapper.RoleMapper;
import com.ding.hyld.service.RoleService;
import com.ding.hyld.vo.RoleVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService{
    @Override
    public List<RoleInfo> getAllRole() {
        return baseMapper.getAllRole();
    }

    @Override
    public void addRole(RoleVo roleVo) {
        baseMapper.addRole(roleVo);
    }

    @Override
    public void updateRole(RoleVo roleVo) {
        baseMapper.updateRole(roleVo);
    }

    @Override
    public void saveRolePower(RoleVo roleVo) {
        baseMapper.saveRolePower(roleVo);
    }

    @Override
    public void deleteRolePower(Integer roleId) {
        baseMapper.deleteRolePower(roleId);
    }
}
