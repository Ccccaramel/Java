package com.ding.hyld.controller;

import com.alibaba.fastjson.JSON;
import com.ding.hyld.constant.SystemConfigKey;
import com.ding.hyld.controller.Base.BaseController;
import com.ding.hyld.entity.User;
import com.ding.hyld.info.SystemConfigInfo;
import com.ding.hyld.service.LoginService;
import com.ding.hyld.service.QQUserService;
import com.ding.hyld.service.SystemConfigService;
import com.ding.hyld.service.UserService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.RsaUtils;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.QQUserVo;
import com.ding.hyld.vo.UserVo;
import com.ding.hyld.vo.VisitLogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
