package com.base.annotation;

public class D extends C {
    @Override
    public void showA() {
        System.out.println("showA.");
    }
    @Override
    public void funB() {
        System.out.println("funB.");
    }

    public void funD(){
        System.out.println("funD.");
    }

    public D(String ... strings) {
        for (String s:strings) System.out.println(s);
    }
}
