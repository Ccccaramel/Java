package com.ding.office.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.office.entity.Role;
import com.ding.office.info.RoleInfo;
import com.ding.office.vo.RoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<RoleInfo> getAllRole();

    void addRole(@Param("roleVo") RoleVo roleVo);

    void updateRole(@Param("roleVo") RoleVo roleVo);

    void saveRolePower(@Param("roleVo") RoleVo roleVo);

    void deleteRolePower(Integer roleId);

    RoleInfo findById(Integer id);
}
