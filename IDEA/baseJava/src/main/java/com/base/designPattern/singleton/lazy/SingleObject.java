package com.base.designPattern.singleton.lazy;

/**
 * 饿汉式
 */
public class SingleObject {
    private static SingleObject instance = new SingleObject();
    private SingleObject(){}
    public static SingleObject getInstance(){
        return instance;
    }
    public void showMessage(){
        System.out.println("SingleObject.showMessage()");
    }
}