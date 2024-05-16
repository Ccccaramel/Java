package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.Role;
import com.ding.office.info.RoleInfo;
import com.ding.office.vo.RoleVo;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<RoleInfo> getAllRole();

    void addRole(RoleVo roleVo);

    void updateRole(RoleVo roleVo);

    void saveRolePower(RoleVo roleVo);

    void deleteRolePower(Integer roleId);

    RoleInfo findById(Integer id);
}
