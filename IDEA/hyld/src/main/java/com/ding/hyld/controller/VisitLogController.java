package com.ding.hyld.controller;

import com.alibaba.fastjson.JSON;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.service.UpdateLogService;
import com.ding.hyld.service.VisitLogService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.RsaUtils;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UpdateLogVo;
import com.ding.hyld.vo.VisitLogVo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/searchVisitLog")
    public R searchVisitLog(Page page, VisitLogVo visitLogVo){
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",visitLogService.searchVisitLog(page, visitLogVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(visitLogService.searchVisitLog(null,visitLogVo).size()*1.0/page.getSize()));
        }
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
        return R.success("2333");
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
