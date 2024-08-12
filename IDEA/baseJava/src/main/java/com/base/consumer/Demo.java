package com.base.consumer;

import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

public class Demo {
    @Test
    public void fun1(){
        Consumer<String> print = (s)->{
            System.out.println("s:"+s);
        };
        print.accept("hello!");
    }
}
