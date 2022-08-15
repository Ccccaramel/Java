package com.ding.hyld.constant;

public enum ResponseCode {
    API_SUCCESS(105,"对外接口成功标记"),
    API_FAIL(106,"对外接口失败标记"),
    SUCCESS(1,"成功标记"),
    FAIL(0,"失败标记");

    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
