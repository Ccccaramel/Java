package com.springMVC.config;

import com.springMVC.Interceptor.TestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.List;
import java.util.Properties;

/**
 * 代替 springMVC 的配置文件
 * 1.扫面组件
 * 2.视图解析器
 * 3.view-controller
 * 4.default-servlet-handler
 * 5.mvc 注解驱动
 * 6.文件上传解析器
 * 7.异常处理
 * 8.拦截器
 */
@Configuration // 将当前类标识为一个配置类
@ComponentScan("com.springMVC.controller") // 扫描组件
@EnableWebMvc // mvc 注解驱动
public class WebConfig implements WebMvcConfigurer {
    // 配置生成模板解析器
    @Bean
    public ITemplateResolver templateResolver(){
        WebApplicationContext webApplicationContext= ContextLoader.getCurrentWebApplicationContext();
        ServletContextTemplateResolver templateResolver=new ServletContextTemplateResolver(webApplicationContext.getServletContext());
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    // 生成模板引擎并为引擎注入模板解析器
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver){
        SpringTemplateEngine templateEngine=new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    // 生成视图解析器并为解析器注入模板引擎
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine){
        ThymeleafViewResolver viewResolver=new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }

    // 配置生成模板解析器
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    // 配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        TestInterceptor testInterceptor =new TestInterceptor();
        registry.addInterceptor(testInterceptor).addPathPatterns("/**");
    }

    // 配置视图解析器,这样就不用在控制器里写请求映射了
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
    }

    // 文件上传解析器
    @Bean
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver();
        return commonsMultipartResolver;
    }

    // 配置异常处理
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver=new SimpleMappingExceptionResolver();
        Properties properties=new Properties();
        properties.setProperty("java.lang.ArithmeticException","error");
//        properties.setProperty("exceptionAttribute","ex"); // 设置将异常信息共享在请求域中的键
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        simpleMappingExceptionResolver.setExceptionAttribute("ex");
        resolvers.add(simpleMappingExceptionResolver);
    }
}
