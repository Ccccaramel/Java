package com.ding.hyld.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ding.hyld.entity.User;
import com.ding.hyld.mapper.UserMapper;
import com.ding.hyld.service.LoginService;
import com.ding.hyld.service.UserService;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.LoginUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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
        User user= loginService.checkToken(token);
        if (user==null){
            return R.fail("token 不合法!");
        }
        LoginUserVo loginUserVo=new LoginUserVo();
        loginUserVo.setId(user.getId());
        loginUserVo.setName(user.getName());
        return R.success(loginUserVo);
    }
}
