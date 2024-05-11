package com.base.designPattern.singleton.lazy;

public class Start {
    public static void main(String[] args) {
        SingleObject singleObject = SingleObject.getInstance();
        singleObject.showMessage();
    }
}
