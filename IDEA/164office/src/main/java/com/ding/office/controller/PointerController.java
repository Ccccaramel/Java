package com.ding.office.controller;

import com.ding.office.constant.DictionaryCode;
import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.PointerService;
import com.ding.office.service.impl.QQIPServiceImpl;
import com.ding.office.utils.IpUtils;
import com.ding.office.utils.R;
import com.ding.office.vo.Page;
import com.ding.office.vo.PointerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/pointer")
public class PointerController extends BaseController {
    @Autowired
    private PointerService pointerService;
    @Autowired
    private QQIPServiceImpl ibsService;
    /**
     * @param page
     * @param pointerVo
     * @return
     */
    @GetMapping("/searchPointer")
    public R searchPointer(Page page, PointerVo pointerVo){
        if(pointerVo.isShow() || !(isLogin() && getCurrentUser().getUser().getRole().equals(DictionaryCode.USER_ROLE_1))){
            pointerVo.setStatus(DictionaryCode.POINTER_STATUS_1);
        }
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",pointerService.searchPointer(page, pointerVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(pointerService.searchPointerOfPage(pointerVo)*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/savePointer")
    public R savePointer(@RequestBody PointerVo pointerVo, HttpServletRequest request){

        String ip = IpUtils.getIpAddress(request);
        pointerVo.setIp(ip);
        pointerVo.setAddress(ibsService.getAddress(ip).get("address"));

        pointerVo.setUserId(getUserId());
        pointerVo.setStatus(DictionaryCode.POINTER_STATUS_1);
        pointerService.add(pointerVo);
        return R.success("反馈已提交!");
    }

    @PreAuthorize("hasAuthority('pointerManage_reply')")
    @PostMapping("/replyPointer")
    public R replyPointer(@RequestBody PointerVo pointerVo){
        pointerVo.setReplyTime(LocalDateTime.now());
        pointerService.reply(pointerVo);
        return R.success("反馈回复成功!");
    }

    @PreAuthorize("hasAuthority('pointerManage_delete')")
    @PostMapping("/deletePointer")
    public R deletePointer(@RequestBody PointerVo pointerVo){
        pointerVo.setStatus(DictionaryCode.POINTER_STATUS_2);
        pointerService.updateStatus(pointerVo);
        return R.success("反馈已删除!");
    }

    @PreAuthorize("hasAuthority('pointerManage_return')")
    @PostMapping("/returnPointer")
    public R returnPointer(@RequestBody PointerVo pointerVo){
        pointerVo.setStatus(DictionaryCode.POINTER_STATUS_1);
        pointerService.updateStatus(pointerVo);
        return R.success("反馈已恢复!");
    }
}
