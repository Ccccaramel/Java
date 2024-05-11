package com.base.designPattern.singleton.enums;

/**
 * 枚举
 */
public enum SingleObject {
    INSTANCE;
    public void showMessage(){
        System.out.println("SingleObject.showMessage()");
    }
}
