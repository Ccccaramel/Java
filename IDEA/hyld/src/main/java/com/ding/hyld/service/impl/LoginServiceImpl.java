package com.ding.hyld.service.impl;

import com.alibaba.fastjson.JSON;
import com.ding.hyld.constant.CommonCode;
import com.ding.hyld.entity.User;
import com.ding.hyld.security.CurrentUser;
import com.ding.hyld.service.LoginService;
import com.ding.hyld.service.UserService;
import com.ding.hyld.utils.JWTUtils;
import com.ding.hyld.utils.R;
import com.ding.hyld.vo.UserVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 1.检查参数是否合法
     * 2.根据用户户名和密码去 user 表中查询是否存在
     *   a.不存在,登录失败
     *   b.存在,使用 JWT 生成 token 返回给前端
     * 3.token 存入 redis 中
     *   redis 以键值对 token:user 保存 user 信息,并设置过期时间
     * 4.双重保障
     *   登录时先认证 token 是否合法,再去 redis 认证是否存在
     *
     * @param userVo
     * @return
     */
    @Override
    public R login(UserVo userVo,long timeout,TimeUnit unit) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userVo.getAccount(),userVo.getPassword() + CommonCode.SLAT);
        Authentication authenticate;
        try{
            authenticate = authenticationManager.authenticate(authenticationToken);
        }catch (Exception e1){
            return R.fail("用户名或密码错误!");
        }

        // 认证通过,使用 userId 生成一个 jwt/token
        CurrentUser currentUser = (CurrentUser)authenticate.getPrincipal();
        String token = JWTUtils.createToken(Long.valueOf(currentUser.getUser().getId()));

        // 把完整用户信息存入 redis , userId 作为 key
        redisTemplate.opsForValue().set("login_"+currentUser.getUser().getId(), JSON.toJSONString(currentUser)); // 1天后过期

        return R.success(token,"欢迎回来!(*^▽^*)");
    }

    @Override
    public CurrentUser checkToken(String token) {
        if(token==null){
            return null;
        }
        Map<String,Object> objectMap = JWTUtils.checkToken(token);
        if(objectMap==null){
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("login_" + objectMap.get("userId"));
        if(userJson==null){
            return null;
        }
        CurrentUser currentUser=JSON.parseObject(userJson,CurrentUser.class); // 将字符串转换为对象
        return currentUser;
    }

    @Override
    public void logout() {
        // 获取 SecurityContextHolder 中的用户ID
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        CurrentUser currentUser = (CurrentUser)authentication.getPrincipal();
        Integer userId=currentUser.getUser().getId();
//        String token = JWTUtils.createToken(Long.valueOf(userId));

        // 删除 redis 中的值
        redisTemplate.delete("login_"+userId);
    }

    /**
     * 另一种加密方式
     * @param args
     */
    public static void main(String[] args) {
        String pw = "123" + CommonCode.SLAT;

        System.out.println(DigestUtils.md2Hex(pw));
        System.out.println(DigestUtils.md2Hex(pw));

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String s1 = passwordEncoder.encode(pw);
        String s2 = passwordEncoder.encode(pw);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(passwordEncoder.matches(pw,s2));

        String s3 = "$2a$10$LsHwzUefUw.9T7nN2JCzkugAxmK7CWCAxUcS5QhPF9R2YbNyrEk3C";
        System.out.println(passwordEncoder.matches(pw,s3));
    }
}
