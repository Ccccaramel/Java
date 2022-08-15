package com.ding.hyld.service;

import com.ding.hyld.entity.User;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.UserVo;

import java.util.concurrent.TimeUnit;

public interface LoginService {
    R login(UserVo userVo, long timeout, TimeUnit days);

    User checkToken(String token);
}
