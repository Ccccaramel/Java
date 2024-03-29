package com.ding.hyld.config;

import com.ding.hyld.constant.CommonCode;
import com.ding.hyld.filter.JWTAuthenticationTokenFilter;
import com.ding.hyld.interceptor.ResourceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 请求认证过滤器
 *   EnableGlobalMethodSecurity 权限
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启注解配置权限
public class SecurityConfig implements WebMvcConfigurer {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private JWTAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Bean
    public AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 加密
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf().disable() // 关闭 csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 不通过 session 获取 SecurityContext
                .and()
                .authorizeRequests()
                .antMatchers("/user/login").anonymous() // 放行的接口并允许匿名访问
                .antMatchers(CommonCode.OPEN_URL).permitAll() // 直接放行
                .anyRequest().authenticated() // 除上述放行的接口都必须经过认证才能访问
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

//    private static final String[] CLASSPATH_RESOURCE_LOCATINOS={
//            "file:D:/hyldResources/", "classpath:/static/"
//    };
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATINOS);
//    }
    @Value("${spring.web.resources.static-locations}")
    private String[] staticLocations;

    // 添加资源处理器路径 即每次访问静态资源都得添加"/hyld/",例如localhost:8080/hyld/j1.jpg(本地开发测试)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/hyld/**") // url 格式,(在 CommonCode.OPEN_URL 放行该请求路径)
                .addResourceLocations(staticLocations);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("SecurityConfig >>> addInterceptors");
        registry.addInterceptor(new ResourceInterceptor()) // 添加拦截器
                .addPathPatterns("/**") // 拦截路径,包括静态资源
                .excludePathPatterns(CommonCode.OPEN_URL); // 放行请求
    }
}
