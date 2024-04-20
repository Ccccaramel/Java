package com.springboot.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
/**
 * 对所有通过 RequestDispatcher 对象的 forward() 方法请求进行过滤操作
 */
@WebFilter(filterName = "ForwardFilter",urlPatterns = "/*",dispatcherTypes = DispatcherType.FORWARD)
public class ForwardFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("ForwardFilter init()");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("ForwardFilter doFilter() -- start");
        chain.doFilter(request,response);
        log.info("ForwardFilter doFilter() -- end");
    }

    @Override
    public void destroy() {
        log.info("ForwardFilter destroy()");
        Filter.super.destroy();
    }
}
