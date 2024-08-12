package com.base.accessModifier.packageA;

import org.junit.jupiter.api.Test;

public class Demo {
    @Test
    public void test1() {
        A a = new A();
        a.fun1();
        a.fun2();

        B b= new B();
        b.fun1();
        b.fun2();
    }
}