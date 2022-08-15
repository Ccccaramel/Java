package com.base.chuangXiang;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 将科学计数法的数字转换成完全显示的形式,且不改变其它正常的形式
 */
public class ChuangTest {
    public static void main(String[] args) {
        Double d1=1.87710711017E11;
        String value1 = new DecimalFormat("#.###").format(d1);
        System.out.println("v1:"+value1);

        Double d2=1.88;
        String value2 = new DecimalFormat("#.###").format(d2);
        System.out.println("v2:"+value2);

        Long d3=15971366760L;
        String value3 = new DecimalFormat("#.###").format(d3);
        System.out.println("v3:"+value3);

        String regex = "^1\\d{10}$";
        System.out.println(">"+"15971366760".matches(regex));

        // ***

        if(Arrays.asList(1,2).contains(0)){
            System.out.println("T");
        }else{
            System.out.println("F");
        }

        BigDecimal a= BigDecimal.valueOf(0.1);
        BigDecimal b= BigDecimal.valueOf(10.1);
        System.out.println(a.add(b));

        String s="123";
        testT(s);
        List<String> list=new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        testT(list);

        List<A> aList=new ArrayList<>();
        aList.add(new A(1));
        aList.add(new A(2));
        aList.add(new A(3));
        testT(aList);


        String s2=",1,2,3,4,";
        List<String> list2 = Arrays.asList(s2.split(","));
        System.out.println("list2:"+list2);

        String s3="";
        List<String> list3 = Arrays.asList(s3.split(","));
        System.out.println("list3:"+list3);

        String s4=",12,";
        System.out.println("exits:"+Arrays.asList("",",").contains(s4));

        System.out.println("date:"+LocalDateTime.now());
    }
    public static <T> T testT(T t) {
        System.out.println("T>>>"+t);
        return null;
    }
    public static <T> T testT(List<T> t) {
        System.out.println("T>>>"+t);
        return null;
    }
}

class A{
    public int a;

    public A(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                '}';
    }
}
