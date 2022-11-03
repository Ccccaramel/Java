package com.ding.hyld.controller;

import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.MenuService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.TreeUtils;
import com.ding.hyld.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
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
}
