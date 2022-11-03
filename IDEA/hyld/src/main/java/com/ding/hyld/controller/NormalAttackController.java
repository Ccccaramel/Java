package com.ding.hyld.controller;

import com.alibaba.fastjson.JSONObject;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.NormalAttackService;
import com.ding.hyld.service.StarPowerService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.ResourceUploadAndDownloadUtils;
import com.ding.hyld.utils.ResourcesPathUtils;
import com.ding.hyld.vo.NormalAttackVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.StarPowerVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
            result.put("totalPage",Math.ceil(normalAttackService.searchNormalAttack(null,normalAttackVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

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
