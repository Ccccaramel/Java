package com.ding.office.controller;

import com.ding.office.controller.Base.BaseController;
import com.ding.office.service.QQUserService;
import com.ding.office.utils.R;
import com.ding.office.vo.QQUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qqUser")
@Slf4j
public class QQUserController extends BaseController {
    @Autowired
    private QQUserService qqUserService;

    @PostMapping("/qqUserInfoRecord")
    public R qqUserInfoRecord(QQUserVo qqUserVo){
        qqUserService.qqUserInfoRecord(qqUserVo);
        return R.success();
    }

}
