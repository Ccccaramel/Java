package com.springboot.filter;

import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@Slf4j
// 过滤器的执行顺序,值越小,越先执行
//@Order(1)
//@Component
/**
 * 关于注册过滤器,将此类标记为组件,让 spring 来管理,也可以不加这个@Component ,而是在启动类上新增注解 @ServletComponentScan
 *  1.使用 @Component 注解,可以使用 @Order 来指定过滤器执行顺序,但 @WebFilter 将会失效
 *  2.在启动类上添加 @ServletComponentScan 但 @Order 会失效
 * 如果上述两个注解同时使用,将会在容器中重复注册组件,不可取
 * 通过测试,暂时统一使用第二种方式
  */
// 请求映射,或在 web.xml 里配置映射关系, /* 表示对所有请求进行过滤操作,而 / 至拦截静态资源请求,不会拦截动态(jsp)页面的请求,设置 dispatcherTypes 值将只处理识别指定类型的请求,默认 DispatcherType.REQUEST
/**
 * urlPatterns 过滤指定 url
 */
@WebFilter(filterName = "CommonFilter",urlPatterns = {"/car"},initParams = {@WebInitParam(name = "order",value = "1")})
public class CommonFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("CommonFilter init(),order:"+filterConfig.getInitParameter("order"));
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("CommonFilter doFilter() -- start");
        chain.doFilter(request,response);  // 参数 chain 为代表当前 Filter 链的对象,本过滤器放行,交由下个过滤器处理,若不执行则请求无法到达服务器
        log.info("CommonFilter doFilter() -- end");
    }

    @Override
    public void destroy() {
        log.info("CommonFilter destroy()");
        Filter.super.destroy();
    }
}
