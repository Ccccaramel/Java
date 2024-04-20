package com.ding.hyld.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ding.hyld.constant.CommonCode;
import com.ding.hyld.info.ibs.AddressInfo;
import com.ding.hyld.info.ibs.IpInfo;
import com.ding.hyld.info.ibs.WeatherInfo;
import com.ding.hyld.service.QQIPService;
import com.ding.hyld.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
public class BaiduWeatherServiceImpl {

    @Autowired
    private QQIPService qqIPService;
    @Autowired
    private BaiduWeatherServiceImpl baiduWeatherService;
    /**
     * 先通过ip获取行政区划代码并查询天气信息
     * @param ips
     * @return
     */
    public R getWeather(String ips){
        Integer adcode;
        try{
            IpInfo ipInfo = JSONObject.toJavaObject(qqIPService.ip(CommonCode.QQ_IP_IBS_KEY,ips), IpInfo.class);
            AddressInfo addressInfo = ipInfo.getResult().getAd_info();
            adcode=addressInfo.getAdcode();
        }catch (Exception e){
            log.info("IBS请求异常,天气信息的所在地将改为北京市");
            adcode = 110100;
        }
        if(adcode%1000==0){
            adcode+=100;  // adcode 是腾讯提供的,与百度的映射关系有部分不同,主要是省会城市的值不一样,例如腾讯的北京市是110000,而百度是110100,需要修改
        }
//        adcode = 450100;  // 测试

        Map params = new LinkedHashMap<String, String>();
        params.put("district_id", adcode.toString());
        params.put("data_type", "all");
        params.put("ak", CommonCode.BAIDU_WEATHER_IBS_AK);

        return requestGetAK(CommonCode.BAIDU_WEATHER_IBS_URL, params);
    }

    public R requestGetAK(String strUrl, Map<String, String> param) {
        Map<String,Object> result = new HashMap<>();
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setStatus(0);
        if (strUrl == null || strUrl.length() <= 0 || param == null || param.size() <= 0) {
            return R.fail("参数异常,无法获取天气信息!");
        }

        try {
            StringBuffer queryString = new StringBuffer();
            queryString.append(strUrl);
            for (Map.Entry<?, ?> pair : param.entrySet()) {
                queryString.append(pair.getKey() + "=");
                //    第一种方式使用的 jdk 自带的转码方式  第二种方式使用的 spring 的转码方法 两种均可
//                    queryString.append(URLEncoder.encode((String) pair.getValue(), "UTF-8").replace("+", "%20") + "&");
                queryString.append(UriUtils.encode((String) pair.getValue(), "UTF-8") + "&");
            }

            if (queryString.length() > 0) {
                queryString.deleteCharAt(queryString.length() - 1);
            }

            java.net.URL url = new URL(queryString.toString());
            log.info("queryString:{}",queryString.toString());
            URLConnection httpConnection = null;
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.connect();

            InputStreamReader isr = new InputStreamReader(httpConnection.getInputStream());
            BufferedReader reader = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            isr.close();
            result.put("weatherInfo",JSONObject.parseObject(buffer.toString(),WeatherInfo.class));
            return R.success(result);
        } catch (IOException e) {
            weatherInfo.setMessage("天气信息获取异常!");
            e.printStackTrace();
            result.put("weatherInfo",weatherInfo);
            return R.fail("天气获取失败!");
        }
    }
}
