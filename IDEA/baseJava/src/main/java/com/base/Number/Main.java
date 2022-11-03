package com.base.Number;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        int i;
        Integer integer;
        /**
         * 将科学计数法的数字转换成完全显示的形式,且不改变其它正常的形式
         */
        Double d1=1.87710711017E11;
        String value1 = new DecimalFormat("#.###").format(d1);
        System.out.println("v1:"+value1);

        // 不会影响正常格式的数字
        Double d2=1.88;
        String value2 = new DecimalFormat("#.###").format(d2);
        System.out.println("v2:"+value2);

        // 不会影响正常格式的数字
        Long d3=15971366760L;
        String value3 = new DecimalFormat("#.###").format(d3);
        System.out.println("v3:"+value3);

        // 加法
        BigDecimal a= BigDecimal.valueOf(0.1);
        BigDecimal b= BigDecimal.valueOf(10.1);
        System.out.println(a.add(b));

//        System.out.println(1+i);
//        System.out.println(integer+1);

        // 除法
        Integer i1=145,i2=234;
        System.out.println(">>>"+(100*i1/i2));
    }
}

