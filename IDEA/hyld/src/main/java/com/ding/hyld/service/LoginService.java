package com.ding.hyld.service;

import com.ding.hyld.security.CurrentUser;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.UserVo;

import java.util.concurrent.TimeUnit;

public interface LoginService {
    R login(UserVo userVo, long timeout, TimeUnit days);

    CurrentUser checkToken(String token);

    void logout();
}
