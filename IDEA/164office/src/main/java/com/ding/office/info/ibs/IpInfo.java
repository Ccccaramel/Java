package com.ding.office.info.ibs;

/**
 * ip API 的返回数据结构
 */
public class IpInfo {
    private Integer status; // 状态码，0为正常，其它为异常，详细请参阅状态码说明
    private String message; // 对status的描述
    private ResultInfo result; // IP定位结果

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

    public ResultInfo getResult() {
        return result;
    }

    public void setResult(ResultInfo result) {
        this.result = result;
    }
}
