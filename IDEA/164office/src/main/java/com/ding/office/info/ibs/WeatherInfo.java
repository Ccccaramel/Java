package com.ding.office.info.ibs;

/**
 * weather API 的返回数据结构
 */
public class WeatherInfo {
    private Integer status;  // 状态码
    private String message;  // 备注信息
    private WeatherResultInfo result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WeatherResultInfo getResult() {
        return result;
    }

    public void setResult(WeatherResultInfo result) {
        this.result = result;
    }
}
