package com.ding.office.controller;

import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.UpdateLogService;
import com.ding.office.utils.R;
import com.ding.office.vo.Page;
import com.ding.office.vo.UpdateLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
            result.put("totalPage",Math.ceil(updateLogService.searchUpdateLogOfPage(updateLogVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PreAuthorize("hasAnyAuthority('updateLog_add','updateLog_update')")
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
