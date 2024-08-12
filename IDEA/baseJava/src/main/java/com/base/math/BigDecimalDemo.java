package com.base.math;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class BigDecimalDemo {

    @Test
    public void fun1() {
        BigDecimal bd1=new BigDecimal(101);
        BigDecimal bd2=new BigDecimal(100);
        System.out.println("bd1/bd2="+bd1.divide(bd2));
    }

    @Test
    public void fun2() {
        int bd1=new BigDecimal("0.01").multiply(new BigDecimal(100)).intValue();
        System.out.println("bd1:"+bd1);
    }
}
