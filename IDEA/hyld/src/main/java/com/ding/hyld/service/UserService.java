package com.ding.hyld.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.hyld.entity.User;
import com.ding.hyld.info.UserInfo;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UserVo;

import java.util.List;

public interface UserService extends IService<User> {
    User login(String account, String password);

    R findUserByToken(String token);

    void register(UserVo userVo);

    User findByName(String name);

    UserInfo findById(Integer id);

    void updateUserInfo(UserVo userVo);

    List<UserInfo> searchUser(Page page, UserVo userInfo);

    void saveUser(UserVo userVo);

    void saveUserPassword(UserVo userVo);

    void saveHeadPortrait(UserVo userVo);
}
