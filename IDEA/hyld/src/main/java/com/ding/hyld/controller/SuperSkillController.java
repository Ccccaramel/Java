package com.ding.hyld.controller;

import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.NormalAttackService;
import com.ding.hyld.service.SuperSkillService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.NormalAttackVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.SuperSkillVo;
import org.springframework.beans.factory.annotation.Autowired;
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
            result.put("totalPage",Math.ceil(superSkillService.searchSuperSkill(null,superSkillVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

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
