package com.base.Number;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 将科学计数法的数字转换成完全显示的形式,
 */
public class Main {
    public static void main(String[] args) {

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
}

