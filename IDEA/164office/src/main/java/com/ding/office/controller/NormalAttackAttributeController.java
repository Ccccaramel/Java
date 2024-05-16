package com.ding.office.controller;

import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.NormalAttackAttributeService;
import com.ding.office.utils.R;
import com.ding.office.vo.NormalAttackAttributeVo;
import com.ding.office.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/normalAttackAttribute")
public class NormalAttackAttributeController extends BaseController {
    @Autowired
    private NormalAttackAttributeService normalAttackAttributeService;

    @GetMapping("/searchNormalAttackAttribute")
    public R searchStarPower(Page page, NormalAttackAttributeVo normalAttackAttributeVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",normalAttackAttributeService.searchNormalAttackAttribute(page, normalAttackAttributeVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(normalAttackAttributeService.searchNormalAttackAttributeOfPage(normalAttackAttributeVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PreAuthorize("hasAuthority('gameRoleManage_normalAttack')")
    @PostMapping("/saveNormalAttackAttribute")
    public R saveNormalAttackAttribute(@RequestBody NormalAttackAttributeVo normalAttackAttributeVo){
        if(normalAttackAttributeVo.isAdd()){
            normalAttackAttributeService.add(normalAttackAttributeVo);
            return R.success("新增普通攻击属性成功!");
        }
        else{
            normalAttackAttributeService.update(normalAttackAttributeVo);
            return R.success("修改普通攻击属性成功!");
        }
    }

    @PostMapping("/deleteNormalAttackAttribute")
    public R deleteNormalAttackAttribute(@RequestBody NormalAttackAttributeVo normalAttackAttributeVo){
        normalAttackAttributeService.delete(normalAttackAttributeVo);
        return R.success("删除普通攻击属性成功!");
    }
}
