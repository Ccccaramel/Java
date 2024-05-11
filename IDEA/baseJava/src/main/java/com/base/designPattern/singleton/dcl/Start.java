package com.base.designPattern.singleton.dcl;


public class Start {
    public static void main(String[] args) {
        SingleObject singleObject = SingleObject.getInstance();
        singleObject.showMessage();
    }
}
