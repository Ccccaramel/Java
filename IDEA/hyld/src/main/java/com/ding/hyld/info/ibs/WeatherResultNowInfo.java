package com.ding.hyld.info.ibs;

/**
 * 实时信息
 */
public class WeatherResultNowInfo {
    private String text;  // 当前天气情况
    private Integer temp;  // 当前温度
    private Integer feels_like;  // 当前体感温度(℃)
    private Integer rh;  // 当前相对湿度(%)
    private String wind_class;  // 当前风力等级
    private String wind_dir;  // 当前风向
    private String uptime;  // 更新时间

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(Integer feels_like) {
        this.feels_like = feels_like;
    }

    public Integer getRh() {
        return rh;
    }

    public void setRh(Integer rh) {
        this.rh = rh;
    }

    public String getWind_class() {
        return wind_class;
    }

    public void setWind_class(String wind_class) {
        this.wind_class = wind_class;
    }

    public String getWind_dir() {
        return wind_dir;
    }

    public void setWind_dir(String wind_dir) {
        this.wind_dir = wind_dir;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }
}
