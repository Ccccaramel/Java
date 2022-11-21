package com.ding.hyld.controller;

import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.OfficialVersionUpdateLogService;
import com.ding.hyld.service.UpdateLogService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.OfficialVersionUpdateLogVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UpdateLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/officialVersionUpdateLog")
public class OfficialVersionUpdateLogController extends BaseController {
    @Autowired
    private OfficialVersionUpdateLogService officialVersionUpdateLogService;

    @GetMapping("/searchOfficialVersionUpdateLog")
    public R searchOfficialVersionUpdateLog(Page page, OfficialVersionUpdateLogVo officialVersionUpdateLogVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",officialVersionUpdateLogService.searchOfficialVersionUpdateLog(page, officialVersionUpdateLogVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(officialVersionUpdateLogService.searchOfficialVersionUpdateLog(null,officialVersionUpdateLogVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PreAuthorize("hasAnyAuthority('officialVersionUpdateLogManage_add','officialVersionUpdateLogManage_update')")
    @PostMapping("/saveOfficialVersionUpdateLog")
    public R saveOfficialVersionUpdateLog(@RequestBody OfficialVersionUpdateLogVo officialVersionUpdateLogVo){
        if(officialVersionUpdateLogVo.isAdd()){
            officialVersionUpdateLogService.add(officialVersionUpdateLogVo);
            return R.success("新增官方更新日志成功!");
        }
        else{
            officialVersionUpdateLogService.update(officialVersionUpdateLogVo);
            return R.success("修改官方更新日志成功!");
        }
    }

    @PreAuthorize("hasAuthority('officialVersionUpdateLogManage_delete')")
    @PostMapping("/deleteOfficialVersionUpdateLog")
    public R deleteOfficialVersionUpdateLog(@RequestBody OfficialVersionUpdateLogVo officialVersionUpdateLogVo){
        officialVersionUpdateLogService.delete(officialVersionUpdateLogVo);
        return R.success("官方更新日志删除成功!");
    }
}
