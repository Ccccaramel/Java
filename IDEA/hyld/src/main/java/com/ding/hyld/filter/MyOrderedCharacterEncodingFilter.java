package com.ding.hyld.filter;

import com.alibaba.fastjson.JSON;
import com.ding.hyld.aspect.WebLogAspect;
import com.ding.hyld.service.VisitLogService;
import com.ding.hyld.utils.IpUtils;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.SpringUtils;
import com.ding.hyld.vo.VisitLogVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

/**
 * url 过滤
 */
@Component
public class MyOrderedCharacterEncodingFilter extends OrderedCharacterEncodingFilter {
    private final static Logger logger = LoggerFactory.getLogger(MyOrderedCharacterEncodingFilter.class);

    @Autowired
    VisitLogService visitLogService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("IP(new)            : {}", IpUtils.getIpAddr(request));

        request.setCharacterEncoding("utf-8");
        String ip = request.getHeader("x-forwarded-for");
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        // 打印请求相关参数
        logger.info("Host               : {}",request.getHeader("host"));
        logger.info("X-Real-IP          : {}",request.getHeader("x-real-ip"));
        logger.info("IP                 : {}",ip);
        logger.info("X-Forwarded-Proto  : {}",request.getHeader("x-forwarded-proto"));
        logger.info("URL           : {}",url);
        logger.info("HTTP Method   : {}",request.getMethod());


        boolean check = url.contains("%3B");
        logger.info("check           : {}",check);
        logger.info(">>> {},{},{}",ip,check,SpringUtils.getActiveProfile());
        if((ip==null||check)&&SpringUtils.getActiveProfile().equals("prod")){
            logger.info("ip 为空或 url 包含非法字符!");
            VisitLogVo visitLogVo = new VisitLogVo();
            visitLogVo.setNote("非法请求:"+url+"; method:"+method);
            visitLogService.add(visitLogVo);

            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println(JSON.toJSONString(R.fail("你干嘛～啊哈～哎呦～你好烦～")));
            return;
        }
        try {
            super.doFilterInternal(request, response, filterChain);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("失败！");

            VisitLogVo visitLogVo = new VisitLogVo();
            visitLogVo.setNote("非法请求:"+url+"; method:"+method);
            visitLogService.add(visitLogVo);

            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println(JSON.toJSONString(R.fail("你干嘛～啊哈～哎呦～你好烦～～～")));
            return;
        }
    }
}
