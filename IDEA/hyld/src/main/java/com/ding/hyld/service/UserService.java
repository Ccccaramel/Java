package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.User;
import com.ding.hyld.utils.R;

public interface UserService extends IService<User> {
    User login(String account, String password);

    R findUserByToken(String token);
}
