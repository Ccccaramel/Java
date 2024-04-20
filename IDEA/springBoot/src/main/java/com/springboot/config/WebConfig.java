package com.springboot.config;

import com.springboot.interceptor.CommonInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 注册拦截器并配置
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private CommonInterceptor commonInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor)  // 交给谁处理
                .addPathPatterns("/**");  // 拦截所有控制器的请求
    }
}
