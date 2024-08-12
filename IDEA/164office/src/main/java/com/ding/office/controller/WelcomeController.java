package com.ding.office.controller;

import com.ding.office.constant.CommonCode;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.UserService;
import com.ding.office.service.VisitLogService;
import com.ding.office.service.impl.QQIPServiceImpl;
import com.ding.office.utils.IpUtils;
import com.ding.office.utils.R;
import com.ding.office.utils.TimeUtils;
import com.ding.office.vo.Page;
import com.ding.office.vo.UserVo;
import com.ding.office.vo.VisitLogVo;
import com.ding.office.vo.WelcomeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 页面访问记录
 */
@Slf4j
@RestController
@RequestMapping("/welcome")
public class WelcomeController extends BaseController {
    @Autowired
    private VisitLogService visitLogService;
    @Autowired
    private UserService userService;
    @Autowired
    private QQIPServiceImpl ibsService;

    @PreAuthorize("hasAuthority('visitLog')")
    @GetMapping("/searchVisitLog")
    public R searchVisitLog(Page page, VisitLogVo visitLogVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",visitLogService.searchVisitLog(page, visitLogVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(visitLogService.searchVisitLogOfPage(visitLogVo)*1.0/page.getSize()));
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
        result.put("numberOfRegistrantsInThisMonth",userService.searchUserOfPage( userVo));
        VisitLogVo visitLogVo = new VisitLogVo();
        visitLogVo.setNote("访问首页");
        result.put("totalVisits",visitLogService.searchVisitLogOfPage(visitLogVo));
        visitLogVo.setStartDate(TimeUtils.getFirstDayOfTheMonthStr());
        result.put("numberOfVisitInThisMonth",visitLogService.searchVisitLogOfPage( visitLogVo));
        visitLogVo.setNote("登录");
        result.put("numberOfLoginInThisMonth",visitLogService.searchVisitLogOfPage( visitLogVo));
        return R.success(result);
    }

    @PostMapping("/myHumbleAbode") // saveVisitLog
    public R myHumbleAbode(@RequestBody WelcomeVo vo, HttpServletRequest request) {
        log.info("key:{}",vo.getKey());
        VisitLogVo visitLogVo = new VisitLogVo();
//        try {
//            visitLogVo = JSON.parseObject(RsaUtils.decryptByPrivateKey(data), VisitLogVo.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        if(isLogin()){
            visitLogVo.setUserId(getUserId());
        }

        String ip = IpUtils.getIpAddress(request);
        visitLogVo.setIp(ip);
        String data=vo.getData()==null ? "" : vo.getData();
        visitLogVo.setNote(CommonCode.ACCESS_TYPE.get(vo.getKey())+data);

        Map<String, String> addressInfo = ibsService.getAddress(ip);
        visitLogVo.setTrueAddress(addressInfo.get("trueAddress"));
        visitLogVo.setAddress(addressInfo.get("address"));

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
