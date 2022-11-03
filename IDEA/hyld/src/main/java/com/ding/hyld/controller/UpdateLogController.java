package com.ding.hyld.controller;

import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.UpdateLogService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UpdateLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/updateLog")
public class UpdateLogController extends BaseController {
    @Autowired
    private UpdateLogService updateLogService;

    @GetMapping("/searchUpdateLog")
    public R searchUpdateLog(Page page, UpdateLogVo updateLogVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",updateLogService.searchUpdateLog(page, updateLogVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(updateLogService.searchUpdateLog(null,updateLogVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/saveUpdateLog")
    public R saveUpdateLog(@RequestBody UpdateLogVo updateLogVo){
        if(updateLogVo.isAdd()){
            updateLogService.add(updateLogVo);
            return R.success("新增更新日志成功!");
        }
        else{
            updateLogService.update(updateLogVo);
            return R.success("修改更新日志成功!");
        }
    }
}
