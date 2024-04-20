package com.ding.hyld.config;

import com.ding.hyld.constant.CommonCode;
import com.ding.hyld.interceptor.ResourceInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

/**
 * 用途:跨域、自定义拦截器、静态资源处理以及 redis 配置
 *     addInterceptors：拦截器
 *     addViewControllers：页面跳转
 *     addResourceHandlers：静态资源
 *     configureDefaultServletHandling：默认静态资源处理器
 *     configureViewResolvers：视图解析器
 *     configureContentNegotiation：配置内容裁决的一些参数
 *     addCorsMappings：跨域
 *     configureMessageConverters：信息转换器
 */
@Slf4j
@EnableCaching
@Configuration
public class CommonConfig extends WebMvcConfigurationSupport {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    /**
     * 跨域设置,否则将拒绝所有请求
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {// 跨域请求
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowCredentials(true)
                .allowedMethods("*")
                .maxAge(3600);
    }

    /**
     * 项目启动时就将自定义的拦截器添加到容器中,用以限制访问权限
     * 有些页面无需登录即可访问
     * 而有些则必须登录后才可访问,直接输入 url 访问会被拦截,前台也可以通过路由判断是否携带 token 进行初步判断,但最终还是需要后台验证才行
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) { // 接口的放行与登录拦截检查
        log.info("CommonConfig >>> addInterceptors");
        registry.addInterceptor(new ResourceInterceptor()) // 添加拦截器
                .addPathPatterns("/**") // 拦截路径,包括静态资源
                .excludePathPatterns(CommonCode.OPEN_URL); // 放行请求
    }

    @Value("${spring.web.resources.static-locations}")
    private String[] staticLocations;

    // 添加资源处理器路径 即每次访问静态资源都得添加"/resources/",例如 http://127.0.0.1:8090/resources/ppp.jpg
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations(staticLocations);
    }

    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}