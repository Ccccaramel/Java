package com.ding.office.service;

import com.ding.office.info.UserInfo;
import com.ding.office.security.CurrentUser;
import com.ding.office.utils.R;
import com.ding.office.vo.QQUserVo;
import com.ding.office.vo.UserVo;
import com.ding.office.vo.VisitLogVo;

import java.util.concurrent.TimeUnit;

public interface LoginService {
    R login(UserVo userVo, long timeout, TimeUnit days, VisitLogVo visitLogVo);

    CurrentUser checkToken(String token);

    UserInfo getUserInfoByToken(String token);

    void logout();

    R qqLogin(UserVo userVo, VisitLogVo visitLogVo, QQUserVo qqUserVo);
}
