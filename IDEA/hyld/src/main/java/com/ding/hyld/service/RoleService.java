package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.Role;
import com.ding.hyld.info.MenuInfo;
import com.ding.hyld.info.RoleInfo;
import com.ding.hyld.vo.RoleVo;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<RoleInfo> getAllRole();

    void addRole(RoleVo roleVo);

    void updateRole(RoleVo roleVo);

    void saveRolePower(RoleVo roleVo);

    void deleteRolePower(Integer roleId);

    RoleInfo findById(Integer id);
}
