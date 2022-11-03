package com.ding.hyld.controller;

import com.ding.hyld.constant.DictionaryCode;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.security.CurrentUser;
import com.ding.hyld.service.PointerService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.TimeUtils;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.PointerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/pointer")
public class PointerController extends BaseController {
    @Autowired
    private PointerService pointerService;

    /**
     * @param page
     * @param pointerVo
     * @return
     */
    @GetMapping("/searchPointer")
    public R searchUpdateLog(Page page, PointerVo pointerVo){
        CurrentUser currentUser = getCurrentUser();
        if(pointerVo.isShow() || currentUser==null || currentUser.getUser().getType().equals(DictionaryCode.USER_TYPE_2)){
            pointerVo.setStatus(DictionaryCode.POINTER_STATUS_1);
        }
        HashMap<String,Object> result=new HashMap<>();
        result.put("data",pointerService.searchPointer(page, pointerVo));
        if(!Objects.equals(page.getSize(),null)){
            result.put("totalPage",Math.ceil(pointerService.searchPointer(null,pointerVo).size()*1.0/page.getSize()));
        }
        return R.success(result);
    }

    @PostMapping("/savePointer")
    public R savePointer(@RequestBody PointerVo pointerVo){
        pointerVo.setUserId(getUserId());
        pointerVo.setStatus(DictionaryCode.POINTER_STATUS_1);
        pointerService.add(pointerVo);
        return R.success("反馈已提交!");
    }

    @PostMapping("/replyPointer")
    public R replyPointer(@RequestBody PointerVo pointerVo){
        pointerVo.setReplyTime(LocalDateTime.now());
        pointerService.reply(pointerVo);
        return R.success("反馈回复成功!");
    }

    @PostMapping("/deletePointer")
    public R deletePointer(@RequestBody PointerVo pointerVo){
        pointerVo.setStatus(DictionaryCode.POINTER_STATUS_2);
        pointerService.updateStatus(pointerVo);
        return R.success("反馈已删除!");
    }

    @PostMapping("/returnPointer")
    public R returnPointer(@RequestBody PointerVo pointerVo){
        pointerVo.setStatus(DictionaryCode.POINTER_STATUS_1);
        pointerService.updateStatus(pointerVo);
        return R.success("反馈已恢复!");
    }
}
