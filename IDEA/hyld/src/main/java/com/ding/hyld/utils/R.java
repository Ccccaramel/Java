package com.ding.hyld.utils;

import com.ding.hyld.constant.ResponseCode;
import java.io.Serializable;

/**
 * @param <T>
 */
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code = ResponseCode.SUCCESS.getCode();

    private String msg = ResponseCode.SUCCESS.getMsg();

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }
    public static <T> R<T> success() {
        return restResult(null, ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
    }

    public static <T> R<T> success(String msg) {
        return restResult(null, ResponseCode.SUCCESS.getCode(), msg);
    }
    public static <T> R<T> fail(String msg) {
        return restResult(null, ResponseCode.FAIL.getCode(), msg);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public static <T> R<T> success(T data) {
        return restResult(data, ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg());
    }

    // 通用,只返回处理结果信息,不用于返回数据
    public static R<ResponseCode> currency(ResponseCode data) {
        return restResult(null, data.getCode(), data.getMsg());
    }

    public static <T> R<T> currency(T data) {
        return restResult(data, ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMsg());
    }

    /**
     * 接口返回成功
     * @param msg
     * @return
     */
    public static <T> R<T> interfaceSuccess(String msg) {
        return restResult(null, ResponseCode.API_SUCCESS.getCode(), ResponseCode.API_SUCCESS.getMsg());
    }

    /**
     * 接口返回成功 ,返回一个对象信息
     * @param msg
     * @return
     */
    public static <T> R<T> interfaceSuccess(String msg,T t) {
        return restResult(t, ResponseCode.API_SUCCESS.getCode(), ResponseCode.API_SUCCESS.getMsg());

    }

    /**
     * 接口返回失败
     * @param msg
     * @return
     */
    public static <T> R<T> interfaceFailed(String msg) {
        return restResult(null, ResponseCode.API_FAIL.getCode(), ResponseCode.API_FAIL.getMsg());
    }

    public R(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public R(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = ResponseCode.FAIL.getCode();
    }
}
