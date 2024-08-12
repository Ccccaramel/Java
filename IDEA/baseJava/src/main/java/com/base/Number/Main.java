package com.base.Number;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 将科学计数法的数字转换成完全显示的形式,
 */
@Slf4j
public class Main {

    @Test
    public void fun1() {

        Double d1=1.87710711017E11;
        String value1 = new DecimalFormat("#.###").format(d1);
        System.out.println("v1:"+value1);

        // 普通类型的数值不受影响
        Double d2=1.88;
        String value2 = new DecimalFormat("#.###").format(d2);
        System.out.println("v2:"+value2);

        Long d3=15971366760L;
        String value3 = new DecimalFormat("#.###").format(d3);
        System.out.println("v3:"+value3);
    }

    /**
     * BigDecimal 转 String
     */
    @Test
    public void fun2(){
        BigDecimal b1=new BigDecimal("11.11");
        System.out.println("b1:"+b1);
    }

    /**
     * long 比较大小
     */
    @Test
    public void fun3(){
        long l1 = 31622400L;
        long l2 = 316224L;
        log.info("l1>l2:{}",l1>l2);
    }
}

