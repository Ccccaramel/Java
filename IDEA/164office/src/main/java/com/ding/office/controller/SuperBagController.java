package com.ding.office.controller;

import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.SuperBagService;
import com.ding.office.utils.R;
import com.ding.office.vo.Page;
import com.ding.office.vo.SuperBagVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/superBag")
public class SuperBagController extends BaseController {
    @Autowired
    private SuperBagService superBagService;

    @PostMapping("/saveSuperBag")
    public R saveSuperBag(@RequestBody SuperBagVo superBagVo){
        String msg;

        if(!isLogin()){
            return R.fail("请先登录!");
        }
        if(!isRoot()){
            return R.fail("暂无权限!");
        }

        if(superBagVo.isAdd()){
            superBagService.add(superBagVo);
            msg="标签新增成功!";
        }
        else{
            superBagService.update(superBagVo);
            msg="标签编辑成功!";
        }

        return R.success(msg);
    }

    @GetMapping("/searchSuperBag")
    public R searchSuperBag(SuperBagVo superBagVo, Page page){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",superBagService.searchSuperBag(superBagVo,page));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(superBagService.searchSuperBagOfPage(superBagVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/deleteSuperBag")
    public R deleteSuperBag(@RequestBody SuperBagVo superBagVo){
        superBagService.delete(superBagVo);
        return R.success("标签已删除!");
    }

}