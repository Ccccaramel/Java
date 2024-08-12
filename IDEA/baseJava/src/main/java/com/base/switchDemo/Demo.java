package com.base.switchDemo;

import org.junit.jupiter.api.Test;

public class Demo {
    @Test
    public void fun1(){
        int i=2;
        switch (i){
            case 1:
                System.out.println(1);
                break;
            case 2:
                System.out.println(2);
                break;
            case 3:
                System.out.println(3);
                break;
            default:
                System.out.println(0);
        }
    }
}
