package com.ding.office.info;

/**
 * IP定位结果
 */
public class QQMapIPResultInfo {
    private String ip; // 用于定位的IP地址
    private QQMapIPResultLocationInfo location;
    private QQMapIPResultAdInfoInfo ad_info;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public QQMapIPResultLocationInfo getLocation() {
        return location;
    }

    public void setLocation(QQMapIPResultLocationInfo location) {
        this.location = location;
    }

    public QQMapIPResultAdInfoInfo getAd_info() {
        return ad_info;
    }

    public void setAd_info(QQMapIPResultAdInfoInfo ad_info) {
        this.ad_info = ad_info;
    }

    @Override
    public String toString() {
        return "QQMapIPResultInfo{" +
                "ip='" + ip + '\'' +
                ", location=" + location +
                ", ad_info=" + ad_info +
                '}';
    }
}
