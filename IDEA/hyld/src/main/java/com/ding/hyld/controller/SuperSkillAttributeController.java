package com.ding.hyld.controller;

import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.NormalAttackAttributeService;
import com.ding.hyld.service.SuperSkillAttributeService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.NormalAttackAttributeVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.SuperSkillAttributeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/superSkillAttribute")
public class SuperSkillAttributeController extends BaseController {
    @Autowired
    private SuperSkillAttributeService superSkillAttributeService;

    @GetMapping("/searchSuperSkillAttribute")
    public R searchSuperSkillAttribute(Page page, SuperSkillAttributeVo superSkillAttributeVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",superSkillAttributeService.searchSuperSkillAttribute(page, superSkillAttributeVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(superSkillAttributeService.searchSuperSkillAttributeOfPage(superSkillAttributeVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PreAuthorize("hasAuthority('gameRoleManage_superSkill')")
    @PostMapping("/saveSuperSkillAttribute")
    public R saveSuperSkillAttribute(@RequestBody SuperSkillAttributeVo superSkillAttributeVo){
        if(superSkillAttributeVo.isAdd()){
            superSkillAttributeService.add(superSkillAttributeVo);
            return R.success("新增超级技能属性成功!");
        }
        else{
            superSkillAttributeService.update(superSkillAttributeVo);
            return R.success("修改超级技能属性成功!");
        }
    }

    @PostMapping("/deleteSuperSkillAttribute")
    public R deleteSuperSkillAttribute(@RequestBody SuperSkillAttributeVo superSkillAttributeVo){
        superSkillAttributeService.delete(superSkillAttributeVo);
        return R.success("删除超级技能属性成功!");
    }
}
