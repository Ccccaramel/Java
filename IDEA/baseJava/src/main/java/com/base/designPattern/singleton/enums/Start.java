package com.base.designPattern.singleton.enums;

public class Start {
    public static void main(String[] args) {
        SingleObject i1 = SingleObject.INSTANCE;
        i1.showMessage();
        SingleObject i2 = SingleObject.INSTANCE;
        i2.showMessage();
        System.out.println(i1==i2);
    }
}
