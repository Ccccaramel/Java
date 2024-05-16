package com.ding.office.controller;

import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.SuperSkillService;
import com.ding.office.utils.R;
import com.ding.office.vo.Page;
import com.ding.office.vo.SuperSkillVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/superSkill")
public class SuperSkillController extends BaseController {
    @Autowired
    private SuperSkillService superSkillService;

    @GetMapping("/searchSuperSkill")
    public R searchSuperSkill(Page page, SuperSkillVo superSkillVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",superSkillService.searchSuperSkill(page, superSkillVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(superSkillService.searchSuperSkillOfPage(superSkillVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PreAuthorize("hasAuthority('gameRoleManage_superSkill')")
    @PostMapping("/saveSuperSkill")
    public R saveSuperSkill(@RequestBody SuperSkillVo superSkillVo){
        if(superSkillVo.isAdd()){
            superSkillService.add(superSkillVo);
            return R.success("新增超级技能成功!");
        }
        else{
            superSkillService.update(superSkillVo);
            return R.success("修改超级技能成功!");
        }
    }

    @PostMapping("/deleteSuperSkill")
    public R deleteSuperSkill(@RequestBody SuperSkillVo superSkillVo){
        superSkillService.delete(superSkillVo);
        return R.success("删除超级技能成功!");
    }
}
