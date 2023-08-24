package com.ding.hyld.filter;

import com.alibaba.fastjson.JSON;
import com.ding.hyld.constant.CommonCode;
import com.ding.hyld.info.QQMapIPInfo;
import com.ding.hyld.info.QQMapIPResultAdInfoInfo;
import com.ding.hyld.service.VisitLogService;
import com.ding.hyld.utils.IpUtils;
import com.ding.hyld.utils.R;
import com.ding.hyld.utils.SpringUtils;
import com.ding.hyld.vo.VisitLogVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.filter.OrderedCharacterEncodingFilter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * url 过滤
 */
@Component
public class MyOrderedCharacterEncodingFilter extends OrderedCharacterEncodingFilter {
    private final static Logger logger = LoggerFactory.getLogger(MyOrderedCharacterEncodingFilter.class);

    @Autowired
    VisitLogService visitLogService;

    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("IP(IpUtils)        : {}", IpUtils.getIpAddress(request));

        request.setCharacterEncoding("utf-8");
        String ip = request.getHeader("x-forwarded-for");
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        // 打印请求相关参数
        logger.info("Host               : {}",request.getHeader("host"));
        logger.info("X-Real-IP          : {}",request.getHeader("x-real-ip"));
        logger.info("IP                 : {}",ip);
        logger.info("X-Forwarded-Proto  : {}",request.getHeader("x-forwarded-proto"));
        logger.info("URL                : {}",url);
        logger.info("HTTP Method        : {}",request.getMethod());

        boolean check = checkUrl(url);
        logger.info("url检查结果         : {}",check);
        logger.info("运行环境            : {}",SpringUtils.getActiveProfile());
        if((ip==null || !check)&&SpringUtils.getActiveProfile().equals("prod")){
            logger.info("ip 为空或 url 包含非法字符!");
            String ips = IpUtils.getIpAddress(request); // 正常情况下 ip 与 ips 的值是一样的,若不同则全部视为非法访问
            if(checkIP(ips)){ // 在未记录到 redis 中
                logger.info("此 {} 不在黑名单中或非 ipv4 ...",ips);
                RestTemplate restTemplate = new RestTemplate();
                String qqMapUrl ="https://apis.map.qq.com/ws/location/v1/ip?key=VQPBZ-GZIKU-QNPV7-B7MD5-PPA2F-TMBES&ip="+ips;
                QQMapIPInfo qqMapIPInfo = restTemplate.getForObject(qqMapUrl,QQMapIPInfo.class);
                logger.info("ip定位信息           : {}",qqMapIPInfo.toString());
                VisitLogVo visitLogVo = new VisitLogVo();
                visitLogVo.setIp(ips);
                if(qqMapIPInfo.getStatus() == 0){ // ip 定位信息查询失败
                    QQMapIPResultAdInfoInfo addInfo = qqMapIPInfo.getResult().getAd_info();
                    visitLogVo.setAddress(addInfo.getNation()+addInfo.getProvince());
                    visitLogVo.setTrueAddress(addInfo.getNation()+addInfo.getProvince()+addInfo.getCity()+addInfo.getDistrict());
                    visitLogVo.setNote("非法请求:"+url+"; method:"+method);
                }else{
                    visitLogVo.setNote("非法请求:"+url+"; method:"+method+"; ip 定位失败:"+qqMapIPInfo.getMessage());
                }
                visitLogService.add(visitLogVo);
            }

            logger.info("响应...");
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().println(JSON.toJSONString(R.fail(CommonCode.STRING_1)));
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
            response.getWriter().println(JSON.toJSONString(R.fail(CommonCode.STRING_1)));
            return;
        }
    }

    private boolean checkUrl(String url) {
        for (String s:Arrays.asList("%3B","%25","forName","Response","121.36.141.65")) {
            if(url.contains(s)){
                logger.info("检测到非法字符       : {}", s);
                return false;
            }
        }
        return true;
    }

    /**
     * 检查 IP 是否在黑名单中
     */
    private boolean checkIP(String ip) {
        logger.info("检查 {} 是否在黑名单中...",ip);
        /**
         * 目前只处理 ip4
         */
        int flag = ip.lastIndexOf(".");
        if(flag!=-1){
            String ipStr = ip.substring(0,flag); // 直接截取前3位
            String blackIpJson = redisTemplate.opsForValue().get("blackIp_" + ipStr);
            if(blackIpJson==null){
                logger.info("此 {} 不在现有黑名单中,更新黑名单...",ip);
                redisTemplate.opsForValue().set("blackIp_"+ipStr, String.valueOf(1),10, TimeUnit.DAYS); // 10天后过期
                logger.info("此 {} 已加入黑名单中...",ip);
                return true;
            }
            else{
                int times = Integer.valueOf(blackIpJson)+1;
                redisTemplate.opsForValue().set("blackIp_"+ipStr, String.valueOf(times),10, TimeUnit.DAYS); // 10天后过期
                logger.info("{} 已列入黑名单,请求次数: {}",ip,times);
                return false;
            }
        }
        logger.info("非 ipv4 暂不处理");
        return true;
    }

}
