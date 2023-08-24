package com.ding.hyld.info.ibs;

/**
 * 定位行政区划信息
 */
public class AddressInfo {
    private String nation; // 国家
    private Integer nation_code; // 国家代码（ISO3166标准3位数字码）
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

    public Integer getNation_code() {
        return nation_code;
    }

    public void setNation_code(Integer nation_code) {
        this.nation_code = nation_code;
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
}
