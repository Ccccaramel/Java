package com.ding.office.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebLogAspect {
    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    // 任意公共方法的执行
//    @Pointcut("execution(public * *(..))")
    @Pointcut("execution(public * com.ding.office.controller.*.*(..))")
    public void webLog(){ }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        // 打印日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 打印请求相关参数
        logger.info("URL           : {}",request.getRequestURL().toString());
        logger.info("HTTP Method   : {}",request.getMethod());
        logger.info("Class Method  : {},{}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
        logger.info("IP            : {}",request.getRemoteAddr());
        logger.info("Request Args  : {}", Arrays.toString(joinPoint.getArgs()));
    }

    @After("webLog()")
    public void doAfter() throws Throwable {
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        logger.info("Response Args    : {}",result.toString());
        logger.info("Time-Consuming   : {}ms",System.currentTimeMillis()-startTime);
        return result;
    }
}
