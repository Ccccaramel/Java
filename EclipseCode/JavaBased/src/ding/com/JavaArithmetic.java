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
roundingModeΪС��ģʽ��
    ROUND_CEILING
    ��� BigDecimal �����ģ����� ROUND_UP ���������Ϊ�������� ROUND_DOWN ������
    ROUND_DOWN
    �Ӳ�������(���ض�)��С��֮ǰ�������֡�
    ROUND_FLOOR
    ��� BigDecimal Ϊ�������� ROUND_UP �����Ϊ�������� ROUND_DOWN ��
    ROUND_HALF_DOWN
    ����������> .5������ ROUND_UP�������� ROUND_DOWN ��
    ROUND_HALF_EVEN
    �������������ߵ�����Ϊ���������� ROUND_HALF_UP �������Ϊż�������� ROUND_HALF_DOWN ��
    ROUND_HALF_UP
    ����������>=.5������ ROUND_UP �������� ROUND_DOWN ��
    ROUND_UNNECESSARY
    �á�α����ģʽ��ʵ����ָ����Ҫ��Ĳ��������Ǿ�ȷ�ģ�����˲���Ҫ���������
    ROUND_UP
    �����ڷ� 0 ����С��(���ض�)֮ǰ�������֡�
 */