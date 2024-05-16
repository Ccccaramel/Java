package com.ding.office.controller;

import com.ding.office.constant.SystemConfigKey;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.SystemConfigService;
import com.ding.office.utils.R;
import com.ding.office.vo.Page;
import com.ding.office.vo.SystemConfigVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/systemConfig")
public class SystemConfigController extends BaseController {
    @Autowired
    private SystemConfigService systemConfigService;

    @GetMapping("/searchSystemConfig")
    public R searchSystemConfig(Page page, SystemConfigVo systemConfigVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data", systemConfigService.searchSystemConfig(page, systemConfigVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(systemConfigService.searchSystemConfigOfPage(systemConfigVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PreAuthorize("hasAnyAuthority('systemConfig_add','systemConfig_update')")
    @PostMapping("/saveSystemConfig")
    public R saveSystemConfig(@RequestBody SystemConfigVo systemConfigVo){
        if(systemConfigVo.isAdd()){
            systemConfigService.add(systemConfigVo);
            return R.success("新增系统配置成功!");
        }
        else{
            systemConfigService.update(systemConfigVo);
            return R.success("修改系统配置成功!");
        }
    }

    @GetMapping("/getCommunityNotice")
    public R getCommunityNotice(){
        return R.success(systemConfigService.findByKey(SystemConfigKey.COMMUNITY_NOTICE));
    }

    @GetMapping("/getHomeNotice")
    public R getHomeNotice(){
        return R.success(systemConfigService.findByKey(SystemConfigKey.HOME_NOTICE));
    }
}
