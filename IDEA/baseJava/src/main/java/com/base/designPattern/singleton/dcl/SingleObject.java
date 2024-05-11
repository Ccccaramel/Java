package com.base.designPattern.singleton.dcl;

/**
 * 双检锁/双重校验锁
 */
public class SingleObject {
    private volatile static SingleObject singleObject;
    private SingleObject(){}
    public static SingleObject getInstance(){
        if(singleObject==null){
            synchronized (SingleObject.class){
                if(singleObject==null){
                    singleObject = new SingleObject();
                }
            }
        }
        return singleObject;
    }
    public void showMessage(){
        System.out.println("SingleObject.showMessage()");
    }
}