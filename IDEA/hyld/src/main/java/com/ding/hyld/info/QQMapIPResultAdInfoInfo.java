package com.ding.hyld.info;

/**
 * 定位行政区划信息
 */
public class QQMapIPResultAdInfoInfo {
    private String nation; // 国家
    private String province; // 省
    private String city; // 市
    private String district; // 区
    private Integer adcode; // 行政区划代码

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getAdcode() {
        return adcode;
    }

    public void setAdcode(Integer adcode) {
        this.adcode = adcode;
    }

    @Override
    public String toString() {
        return "QQMapIPResultAdInfoInfo{" +
                "nation='" + nation + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", adcode=" + adcode +
                '}';
    }
}
