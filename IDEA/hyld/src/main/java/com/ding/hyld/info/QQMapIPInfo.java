package com.ding.hyld.info;

public class QQMapIPInfo {
    private Integer status; // 状态码, 0 为正常,其它为异常,详细请参阅 https://lbs.qq.com/service/webService/webServiceGuide/status
    private String message; // 对 status 的描述
    private QQMapIPResultInfo result;

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

    public QQMapIPResultInfo getResult() {
        return result;
    }

    public void setResult(QQMapIPResultInfo result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "QQMapIPInfo{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
