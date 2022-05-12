package com.base.annotation;

public abstract class C implements A,B {
    @Override
    public void funA() {
        System.out.println("funA.");
    }

    public void funC() {
        System.out.println("funC.");
    }
}
