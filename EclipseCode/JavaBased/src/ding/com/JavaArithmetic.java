package ding.com;
import java.math.*;

import static java.math.BigDecimal.ROUND_HALF_DOWN;

public class JavaArithmetic {
    public static void main(String[] args){
        BigDecimal b1=new BigDecimal("20.00");
        BigDecimal b2=new BigDecimal("60.00");
        double d1= 8d;
        double d2= 25d;
        BigDecimal b3 = new BigDecimal(Double.valueOf(d1));
        BigDecimal b4 = new BigDecimal(Double.valueOf(d2));
        int i1= 16;
        int i2= 5;
        BigDecimal b5 = new BigDecimal(Integer.valueOf(i1));
        BigDecimal b6 = new BigDecimal(Integer.valueOf(i2));

        System.out.println("b1+b2="+b1.add(b2));
        System.out.println("b1-b2="+b1.subtract(b2));
        System.out.println("b1*b2="+b1.multiply(b2));
        System.out.println("b1/b2="+b1.divide(b2,10,ROUND_HALF_DOWN));
        System.out.println("d3*d4="+b3.multiply(b4));
        System.out.println("d5*d6=" + b5.multiply(b6));
    }
}
/*
roundingMode为小数模式；
    ROUND_CEILING
    如果 BigDecimal 是正的，则做 ROUND_UP 操作；如果为负，则做 ROUND_DOWN 操作。
    ROUND_DOWN
    从不在舍弃(即截断)的小数之前增加数字。
    ROUND_FLOOR
    如果 BigDecimal 为正，则作 ROUND_UP ；如果为负，则作 ROUND_DOWN 。
    ROUND_HALF_DOWN
    若舍弃部分> .5，则作 ROUND_UP；否则，作 ROUND_DOWN 。
    ROUND_HALF_EVEN
    如果舍弃部分左边的数字为奇数，则作 ROUND_HALF_UP ；如果它为偶数，则作 ROUND_HALF_DOWN 。
    ROUND_HALF_UP
    若舍弃部分>=.5，则作 ROUND_UP ；否则，作 ROUND_DOWN 。
    ROUND_UNNECESSARY
    该“伪舍入模式”实际是指明所要求的操作必须是精确的，，因此不需要舍入操作。
    ROUND_UP
    总是在非 0 舍弃小数(即截断)之前增加数字。
 */