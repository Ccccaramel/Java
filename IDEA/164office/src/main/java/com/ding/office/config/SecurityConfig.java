package com.ding.office.config;

import com.ding.office.constant.CommonCode;
import com.ding.office.filter.JWTAuthenticationTokenFilter;
import com.ding.office.interceptor.ResourceInterceptor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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

//    @Value("${spring.web.resources.static-locations}")
//    private String[] staticLocations;

    // 添加资源处理器路径 即每次访问静态资源都得添加"/resources/",例如localhost:8080/resources/j1.jpg(本地开发测试)
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**") // url 格式,(在 CommonCode.OPEN_URL 放行该请求路径)
//                .addResourceLocations(staticLocations);
//    }

    /**
     * 项目启动时就将自定义的拦截器添加到容器中,用以限制访问权限
     * 有些页面无需登录即可访问
     * 而有些则必须登录后才可访问,直接输入 url 访问会被拦截,前台也可以通过路由判断是否携带 token 进行初步判断,但最终还是需要后台验证才行
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) { // 接口的放行与登录拦截检查
//        log.info("SecurityConfig >>> addInterceptors");
//        registry.addInterceptor(new ResourceInterceptor()) // 添加拦截器
//                .addPathPatterns("/**") // 拦截路径,包括静态资源
//                .excludePathPatterns(CommonCode.OPEN_URL); // 放行请求
//    }
}