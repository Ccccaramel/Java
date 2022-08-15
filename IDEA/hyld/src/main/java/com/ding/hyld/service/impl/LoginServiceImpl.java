package com.ding.hyld.service.impl;

import com.alibaba.fastjson.JSON;
import com.ding.hyld.entity.User;
import com.ding.hyld.service.LoginService;
import com.ding.hyld.service.UserService;
import com.ding.hyld.utils.JWTUtils;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.UserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
public class LoginServiceImpl implements LoginService {
    public static final String slat="ding";

    @Autowired
    private UserService userService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 1.检查参数是否合法
     * 2.根据用户户名和密码去 user 表中查询是否存在
     * a.不存在,登录失败
     * b.存在,使用 JWT 生成 token 返回给前端
     * 3.token 存入 redis 中
     * redis 以键值对 token:user 保存 user 信息,并设置过期时间
     * 4.双重保障
     * 登录时先认证 token 是否合法,再去 redis 认证是否存在
     *
     * @param userVo
     * @return
     */
    @Override
    public R login(UserVo userVo,long timeout,TimeUnit unit) {
        String account = userVo.getAccount();
        String password = userVo.getPassword();
        if(account.isBlank() || password.isBlank()){
            return R.fail("参数有误!");
        }
        password = DigestUtils.md2Hex(password+slat);
        User user=userService.login(account,password);
        if(user==null){
            return R.fail("用户名或密码不存在!");
        }
        String token = JWTUtils.createToken(Long.valueOf(user.getId()));
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(user),timeout, unit); // 1天后过期
        return R.success(token);
    }

    @Override
    public User checkToken(String token) {
        if(token.isBlank()){
            return null;
        }
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if(stringObjectMap==null){
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if(userJson.isBlank()){
            return null;
        }
        JSON.parseObject(userJson,User.class); // 将字符串转换为对象
        return null;
    }
}
