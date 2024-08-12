package com.base.functionalInterface;

import org.junit.jupiter.api.Test;

public class Demo {

    @Test
    public void test1(){
        InterfaceA a = ()->{
            System.out.println("hello!");
        };
        a.run();
    }
}
