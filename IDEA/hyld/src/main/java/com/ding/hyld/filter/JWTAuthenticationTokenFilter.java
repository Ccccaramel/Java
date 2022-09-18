package com.ding.hyld.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ding.hyld.entity.User;
import com.ding.hyld.security.CurrentUser;
import com.ding.hyld.service.LoginService;
import com.ding.hyld.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * OncePerRequestFilter:每次请求时只执行一次过滤
 */
@Component
public class JWTAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private LoginService loginService;

    @Override
    protected boolean isAsyncStarted(HttpServletRequest request) {
        return super.isAsyncStarted(request);
    }

    /**
     * 目的是根据 token 获取用户信息并保存,没有 token 就放行,有 token 但其值非法就报错
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JWTAuthenticationTokenFilter >>> start,method:"+request.getMethod());

        //放行options请求, 因为浏览器的跨域验证，通过options请求发送
        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        // 获取 token
        String token=request.getHeader("Authorization");
        if(!StringUtils.hasText(token)) { // 没有 token ,放行
            filterChain.doFilter(request,response);
            return;
        }

        // 解析,从 redis 中获取用户信息
        CurrentUser currentUser= loginService.checkToken(token);
         if(Objects.isNull(currentUser)){
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println(JSON.toJSONString(R.fail("token 非法,请先登录!")));
            return;
        }

        // TODO 获取权限信息封装到 Authentication 中
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(currentUser,null,currentUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        // 放行
        filterChain.doFilter(request,response);
        System.out.println("JWTAuthenticationTokenFilter >>> end");
    }

}
