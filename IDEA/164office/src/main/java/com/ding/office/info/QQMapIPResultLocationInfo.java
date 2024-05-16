package com.ding.office.info;

/**
 * 定位坐标。注：IP定位服务精确到市级，该位置为IP地址所属的行政区划政府坐标。
  */
public class QQMapIPResultLocationInfo {
    private Integer lat; // 纬度
    private Integer lng; // 经度

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public Integer getLng() {
        return lng;
    }

    public void setLng(Integer lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "QQMapIPResultLocationInfo{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
