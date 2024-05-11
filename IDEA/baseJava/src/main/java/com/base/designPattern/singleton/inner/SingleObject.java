package com.base.designPattern.singleton.inner;

/**
 * 登记式/静态内部类
 */
public class SingleObject {
    private static class SingletonHolder{
        private static final SingleObject INSTANCE = new SingleObject();
    }
    private SingleObject(){}
    public static final SingleObject getInstance(){
        return SingletonHolder.INSTANCE;
    }
    public void showMessage(){
        System.out.println("SingleObject.showMessage()");
    }
}