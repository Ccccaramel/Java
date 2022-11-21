package com.ding.hyld.service.impl;

import com.ding.hyld.entity.User;
import com.ding.hyld.security.CurrentUser;
import com.ding.hyld.service.MenuService;
import com.ding.hyld.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class CurrentUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        User user = userService.findByName(username);
        if(Objects.isNull(user)){
            throw  new RuntimeException("用户名或密码有误!");
        }

        // 获取当前用户权限
        List<String> permissions = menuService.getCurrentUserPower(user.getRole());
//        List<String> permissions = new ArrayList<>(Arrays.asList("test","admin"));

        // TODO 查询权限信息
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(null,null);
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        return new CurrentUser(user,permissions);
    }
}
