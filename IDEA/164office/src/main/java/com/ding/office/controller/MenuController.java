package com.ding.office.controller;

import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.MenuService;
import com.ding.office.utils.R;
import com.ding.office.utils.TreeUtils;
import com.ding.office.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("menu")
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    @GetMapping("getMenuTree")
    public R getMenuTree(){
        return R.success(TreeUtils.transformation(menuService.getAllMenu(),-1));
    }

    @PreAuthorize("hasAnyAuthority('menuManage_add','menuManage_update')")
    @PostMapping("/saveMenu")
    public R saveMenu(@RequestBody MenuVo menuVo){
        if(menuVo.isAdd()){
            menuService.addMenu(menuVo);
            return R.success("菜单新增成功!");
        }else{
            menuService.updateMenu(menuVo);
            return R.success("菜单修改成功!");
        }
    }

    @PreAuthorize("hasAuthority('menuManage_delete')")
    @PostMapping("/deleteMenu")
    public R deleteMenu(@RequestBody MenuVo menuVo){
        menuService.deleteMenu(menuVo);
        return R.success("菜单删除成功!");
    }
}
