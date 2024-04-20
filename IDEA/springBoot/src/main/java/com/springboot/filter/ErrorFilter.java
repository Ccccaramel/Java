package com.springboot.filter;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "ErrorFilter",urlPatterns = "/*",dispatcherTypes = DispatcherType.ERROR)
public class ErrorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("ErrorFilter init()");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("ErrorFilter doFilter() -- start");
        chain.doFilter(request,response);
        log.info("ErrorFilter doFilter() -- end");
    }

    @Override
    public void destroy() {
        log.info("ErrorFilter destroy()");
        Filter.super.destroy();
    }
}
