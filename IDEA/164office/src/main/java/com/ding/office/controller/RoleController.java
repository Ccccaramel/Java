package com.ding.office.controller;

import com.ding.office.service.MenuService;
import com.ding.office.service.RoleService;
import com.ding.office.utils.R;
import com.ding.office.utils.TreeUtils;
import com.ding.office.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    @GetMapping("getAllRole")
    public R getAllRole(){
        return R.success(roleService.getAllRole());
    }

    @PreAuthorize("hasAnyAuthority('roleManage_add','roleManage_update')")
    @PostMapping("/saveRole")
    public R saveRole(@RequestBody RoleVo roleVo){
        if(roleVo.isAdd()){
            roleService.addRole(roleVo);
            return R.success("角色新增成功!");
        }else{
            roleService.updateRole(roleVo);
            return R.success("角色修改成功!");
        }
    }

    @GetMapping("/getRoleMenuTree")
    public R getRoleMenuTree(Integer roleId){
        return R.success(TreeUtils.transformation(menuService.getRoleMenuTree(roleId),-1));
    }

    @PreAuthorize("hasAuthority('roleManage_powerUpdate')")
    @PostMapping("/saveRolePower")
    public R saveRolePower(@RequestBody RoleVo roleVo){
        roleService.saveRolePower(roleVo);
        return R.success("角色权限更新成功!");
    }

    @PreAuthorize("hasAuthority('roleManage_delete')")
    @PostMapping("/deleteRolePower")
    public R deleteRolePower(@RequestBody RoleVo roleVo){
        roleService.deleteRolePower(roleVo.getId());
        return R.success("角色删除成功!");
    }

}
