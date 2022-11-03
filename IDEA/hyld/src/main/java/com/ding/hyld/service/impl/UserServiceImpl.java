package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.constant.CommonCode;
import com.ding.hyld.entity.User;
import com.ding.hyld.info.UserInfo;
import com.ding.hyld.mapper.UserMapper;
import com.ding.hyld.security.CurrentUser;
import com.ding.hyld.service.LoginService;
import com.ding.hyld.service.UserService;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.RsaUtils;
import com.ding.hyld.vo.LoginUserVo;
import com.ding.hyld.vo.Page;
import com.ding.hyld.vo.UserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    @Autowired
    LoginService loginService;

    @Override
    public User login(String account, String password) {
        return baseMapper.login(account,password);
    }

    /**
     * 1.token 合法性校验
     *   若为空 判断 redis 是否存在,不存在返回错误
     *   存在但校验失败,返回错误
     *   存在且校验成功,返回结果 loginUserVo
     * @param token
     * @return
     */
    @Override
    public R findUserByToken(String token) {
        CurrentUser currentUser= loginService.checkToken(token);
        if (currentUser==null){
            return R.fail("token 不合法!");
        }
        LoginUserVo loginUserVo=new LoginUserVo();
        loginUserVo.setId(currentUser.getUser().getId());
        loginUserVo.setName(currentUser.getUser().getName());
        return R.success(loginUserVo);
    }

    @Override
    public void register(UserVo userVo) {
        userVo.setPassword(DigestUtils.md2Hex(userVo.getPassword()+ CommonCode.SLAT));
        baseMapper.register(userVo);
    }

    @Override
    public User findByName(String name) {
        return baseMapper.findByName(name);
    }

    @Override
    public UserInfo findById(Integer id) {
        return baseMapper.findById(id);
    }

    @Override
    public void updateUserInfo(UserVo userVo) {
        baseMapper.updateUserInfo(userVo);
    }

    @Override
    public List<UserInfo> searchUser(Page page, UserVo userVo) {
        return baseMapper.searchUser(page, userVo);
    }

    @Override
    public void saveUser(UserVo userVo) {
        baseMapper.saveUser(userVo);
    }

    @Override
    public void saveUserPassword(UserVo userVo) {
        try {
            userVo.setPassword(new BCryptPasswordEncoder().encode(RsaUtils.decryptByPrivateKey(userVo.getPassword())+ CommonCode.SLAT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        baseMapper.saveUserPassword(userVo);
    }

    @Override
    public void saveHeadPortrait(UserVo userVo) {
        baseMapper.saveHeadPortrait(userVo);
    }
}
