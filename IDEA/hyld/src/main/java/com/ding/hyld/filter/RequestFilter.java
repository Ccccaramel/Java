package com.ding.hyld.filter;

import com.ding.hyld.utils.JWTUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 通用过滤器
 */
@Order(1)
//如果我们有多个过滤器，那这个Order就可以指定优先级
@Component
//这里可以使用@Compent，也可以不加这个@Component，而是在@SpringBootApplication入口类上新增注解@ServletComponentScan。@Component也可以替换成@Configration
@WebFilter(filterName = "requestFilter",urlPatterns = "/*") // 对所有 controller /* 进行过滤操作
public class RequestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("过滤器初始化···");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("CommonFilter - start");
        HttpServletResponse httpServletResponse=(HttpServletResponse)servletResponse;
//        //允许的请求头字段
        httpServletResponse.setHeader("Access-Control-Allow-Headers","Origin, Authorization, X-Requested-With,Accept-Language, Content-Type, Accept");

//        // 允许的方法.默认允许简单请求:GET,POST,HEAD.  不折腾了,直接全使用GET/POST吧
//        httpServletResponse.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
//
//        //是否允许后续请求携带认证信息（cookies）,该值只能是true,否则不返回
//        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        //指定允许其他域名访问，这里填写你允许进行跨域的主机ip（正式上线时可以动态配置具体允许的域名和IP）
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:8091"); // 前端端口
        //参数 chain 为代表当前 Filter 链的对象
        filterChain.doFilter(servletRequest,httpServletResponse);

        System.out.println("CommonFilter - end");
    }

    @Override
    public  void destroy() {
        System.out.println("过滤器销毁···");
    }


}