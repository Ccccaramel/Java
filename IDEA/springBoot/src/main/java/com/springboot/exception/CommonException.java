package com.springboot.exception;

public class CommonException extends Exception{
    private String type;

    public CommonException(String message, String type) {
        super(message);
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
