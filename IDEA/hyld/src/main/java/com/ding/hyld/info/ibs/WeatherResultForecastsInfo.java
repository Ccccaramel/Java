package com.ding.hyld.info.ibs;

/**
 * 5日预报信息
 */
public class WeatherResultForecastsInfo {
    private String text_day;  // 白天天气
    private String text_night;  // 夜晚天气
    private Integer high;  // 最高气温
    private Integer low;  // 最低气温
    private String wc_day;  // 白天风力等级
    private String wd_day;  // 夜晚风向
    private String wc_night;  // 白天风力等级
    private String wd_night;  // 夜晚风向
    private String date;  // 日期
    private String week;  // 星期

    public String getText_day() {
        return text_day;
    }

    public void setText_day(String text_day) {
        this.text_day = text_day;
    }

    public String getText_night() {
        return text_night;
    }

    public void setText_night(String text_night) {
        this.text_night = text_night;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public String getWc_day() {
        return wc_day;
    }

    public void setWc_day(String wc_day) {
        this.wc_day = wc_day;
    }

    public String getWd_day() {
        return wd_day;
    }

    public void setWd_day(String wd_day) {
        this.wd_day = wd_day;
    }

    public String getWc_night() {
        return wc_night;
    }

    public void setWc_night(String wc_night) {
        this.wc_night = wc_night;
    }

    public String getWd_night() {
        return wd_night;
    }

    public void setWd_night(String wd_night) {
        this.wd_night = wd_night;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
