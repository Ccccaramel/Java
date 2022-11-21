package com.ding.hyld.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ding.hyld.entity.Dictionary;
import com.ding.hyld.entity.Role;
import com.ding.hyld.info.MenuInfo;
import com.ding.hyld.info.RoleInfo;
import com.ding.hyld.vo.RoleVo;
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
