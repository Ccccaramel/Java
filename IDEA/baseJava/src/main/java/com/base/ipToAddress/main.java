package com.base.ipToAddress;


import com.alibaba.fastjson2.JSONObject;

public class main {
    public static void main(String[] args) {
        String url = "http://whois.pconline.com.cn/ipJson.jsp";
        String ip = "223.72.75.159";
        String rspStr = HttpUtils.sendGet(url, "ip=" + ip + "&json=true", "GBK");
        JSONObject obj = JSONObject.parseObject(rspStr);
        String region = obj.getString("pro");
        String city = obj.getString("city");
        System.out.println("所在地址位置：" + region + " " + city);
    }
}
