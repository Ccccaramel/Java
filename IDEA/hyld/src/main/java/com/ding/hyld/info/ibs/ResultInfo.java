package com.ding.hyld.info.ibs;

/**
 * IP定位结果
 */
public class ResultInfo {
    private String ip; // 用于定位的IP地址
    private LocationInfo location;
    private AddressInfo ad_info;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public LocationInfo getLocation() {
        return location;
    }

    public void setLocation(LocationInfo location) {
        this.location = location;
    }

    public AddressInfo getAd_info() {
        return ad_info;
    }

    public void setAd_info(AddressInfo ad_info) {
        this.ad_info = ad_info;
    }
}
