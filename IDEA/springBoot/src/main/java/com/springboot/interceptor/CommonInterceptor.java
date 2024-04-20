package com.springboot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 定义拦截器
@Slf4j
// 注册到容器,交给容器管理
@Component
public class CommonInterceptor implements HandlerInterceptor {
    // 目标资源方法执行前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("CommonInterceptor >>> preHandle()");
        return HandlerInterceptor.super.preHandle(request, response, handler);  // true:放行;false:不放行
    }

    // 目标资源方法执行后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("CommonInterceptor >>> postHandle()");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    // 请求处理完成后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("CommonInterceptor >>> afterCompletion()");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
