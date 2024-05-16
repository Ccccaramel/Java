package com.ding.office.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ding.office.entity.User;
import com.ding.office.info.UserInfo;
import com.ding.office.utils.R;
import com.ding.office.vo.Page;
import com.ding.office.vo.UserVo;

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

    void addEx(Integer userId, int ex);

    User findByQqOpenId(String qqOpenId);

    User findByEmail(String email);

    R bindEmail(UserVo userVo);

    void unbindEmail(Integer userId);

    void updatePassword(User user);

    Integer searchUserOfPage(UserVo userVo);

    void updateCoin(UserVo userVo);

    UserInfo findBriefInfoById(Integer id);
}
