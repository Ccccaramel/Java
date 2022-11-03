package com.ding.hyld.controller;

import com.ding.hyld.service.MenuService;
import com.ding.hyld.service.RoleService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.TreeUtils;
import com.ding.hyld.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/saveRolePower")
    public R saveRolePower(@RequestBody RoleVo roleVo){
        roleService.saveRolePower(roleVo);
        return R.success("角色权限更新成功!");
    }

    @PostMapping("/deleteRolePower")
    public R deleteRolePower(@RequestBody RoleVo roleVo){
        roleService.deleteRolePower(roleVo.getId());
        return R.success("角色删除成功!");
    }

}
