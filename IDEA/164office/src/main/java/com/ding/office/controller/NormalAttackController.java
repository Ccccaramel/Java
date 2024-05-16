package com.ding.office.controller;

import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.NormalAttackService;
import com.ding.office.utils.R;
import com.ding.office.vo.NormalAttackVo;
import com.ding.office.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/normalAttack")
public class NormalAttackController extends BaseController {
    @Autowired
    private NormalAttackService normalAttackService;

    @GetMapping("/searchNormalAttack")
    public R searchNormalAttack(Page page, NormalAttackVo normalAttackVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",normalAttackService.searchNormalAttack(page, normalAttackVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(normalAttackService.searchNormalAttackOfPage(normalAttackVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PreAuthorize("hasAuthority('gameRoleManage_normalAttack')")
    @PostMapping("/saveNormalAttack")
    public R saveNormalAttack(@RequestBody NormalAttackVo normalAttackVo){
        if(normalAttackVo.isAdd()){
            normalAttackService.add(normalAttackVo);
            return R.success("新增普通攻击成功!");
        }
        else{
            normalAttackService.update(normalAttackVo);
            return R.success("修改普通攻击成功!");
        }
    }

    @PostMapping("/deleteNormalAttack")
    public R deleteNormalAttack(@RequestBody NormalAttackVo normalAttackVo){
        normalAttackService.delete(normalAttackVo);
        return R.success("删除普通攻击成功!");
    }

}
