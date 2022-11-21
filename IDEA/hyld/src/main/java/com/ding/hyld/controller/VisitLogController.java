package com.ding.hyld.controller;

import com.alibaba.fastjson.JSON;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.UserService;
import com.ding.hyld.service.VisitLogService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.RsaUtils;
import com.ding.hyld.utils.TimeUtils;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UserVo;
import com.ding.hyld.vo.VisitLogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;

/**
 * Q:为什么使用缩写?
 * A:不想让用户知道该接口的含义和作用
 */
@RestController
@RequestMapping("/vL") // visitLog
public class VisitLogController extends BaseController {
    @Autowired
    private VisitLogService visitLogService;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('visitLog')")
    @GetMapping("/searchVisitLog")
    public R searchVisitLog(Page page, VisitLogVo visitLogVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",visitLogService.searchVisitLog(page, visitLogVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(visitLogService.searchVisitLog(null,visitLogVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

    /**
     * 访问日志 > 获取统计数据
     *   本月新增注册人次   numberOfRegistrantsInThisMonth
     *   本月登录人次      numberOfLoginInThisMonth
     *   本月首页访问人次   numberOfVisitInThisMonth
     *   总访问人次        totalVisits
     * @return
     */
    @PreAuthorize("hasAuthority('visitLog')")
    @GetMapping("/getStatisticalData")
    public R getStatisticalData(){
        HashMap<String,Object> result=new HashMap<>();
        UserVo userVo = new UserVo();
        userVo.setTime(TimeUtils.getFirstDayOfTheMonth());
        result.put("numberOfRegistrantsInThisMonth",userService.searchUser(null, userVo).size());
        VisitLogVo visitLogVo = new VisitLogVo();
        visitLogVo.setTime(TimeUtils.getFirstDayOfTheMonth());
        visitLogVo.setNote("登录");
        result.put("numberOfLoginInThisMonth",visitLogService.searchVisitLog(null, visitLogVo).size());
        visitLogVo.setNote("访问首页");
        result.put("numberOfVisitInThisMonth",visitLogService.searchVisitLog(null, visitLogVo).size());
        result.put("totalVisits",visitLogService.searchVisitLog(null, new VisitLogVo()).size());
        return R.success(result);
    }

    @PostMapping("/sVL") // saveVisitLog
    public R saveVisitLog(@RequestBody String data) {
        VisitLogVo visitLogVo = new VisitLogVo();
        try {
            visitLogVo = JSON.parseObject(RsaUtils.decryptByPrivateKey(data), VisitLogVo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(isLogin()){
            visitLogVo.setUserId(getUserId());
        }
        visitLogService.add(visitLogVo);
        return R.success("success!");
    }

//    @PostMapping("/sVL") // saveVisitLog
//    public R saveVisitLog(@RequestBody VisitLogVo visitLogVo) {
//        if(isLogin()){
//            visitLogVo.setUserId(getUserId());
//        }
//        visitLogService.add(visitLogVo);
//        return R.success("2333");
//    }
}
