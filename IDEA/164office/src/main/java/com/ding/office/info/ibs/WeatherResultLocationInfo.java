package com.ding.office.info.ibs;

/**
 * 位置信息
 */
public class WeatherResultLocationInfo {
    private String country;  // 国家
    private String province;  // 省
    private String city;  // 市
    private String name;  // 县/区
    private String id;  // 县/区ID

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
