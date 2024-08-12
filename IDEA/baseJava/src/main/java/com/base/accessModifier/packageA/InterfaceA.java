package com.base.accessModifier.packageA;

public interface InterfaceA {
    void fun1();

    default void fun2(){
        System.out.println("run fun2()");
    }
}
