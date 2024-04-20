package com.springboot.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
/**
 * 对所有通过 RequestDispatcher 对象的 include() 方法请求进行过滤操作
 */
@WebFilter(filterName = "IncludeFilter",urlPatterns = "/*",dispatcherTypes = DispatcherType.INCLUDE)
public class IncludeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("IncludeFilter init()");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("IncludeFilter doFilter() -- start");
        chain.doFilter(request,response);
        log.info("IncludeFilter doFilter() -- end");
    }

    @Override
    public void destroy() {
        log.info("IncludeFilter destroy()");
        Filter.super.destroy();
    }
}
