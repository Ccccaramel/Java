package com.ding.hyld.service;

import com.ding.hyld.security.CurrentUser;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.QQUserVo;
import com.ding.hyld.vo.UserVo;
import com.ding.hyld.vo.VisitLogVo;

import java.util.concurrent.TimeUnit;

public interface LoginService {
    R login(UserVo userVo, long timeout, TimeUnit days, VisitLogVo visitLogVo);

    CurrentUser checkToken(String token);

    void logout();

    R qqLogin(UserVo userVo, VisitLogVo visitLogVo, QQUserVo qqUserVo);
}
