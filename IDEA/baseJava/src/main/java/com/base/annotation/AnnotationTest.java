package com.base.annotation;

public class AnnotationTest {
    public static void main(String[] args) {

        // 接口 A 是否为接口 B 的父接口
        boolean res=A.class.isAssignableFrom(B.class);
        System.out.println("A>B:"+res);

        // 接口 A 是否为抽象类 C 的接口
        res=A.class.isAssignableFrom(C.class);
        System.out.println("A>C:"+res);

        // 接口 A 是否为类 D 的接口
        res=A.class.isAssignableFrom(D.class);
        System.out.println("A>D:"+res);

        // 接口 B 是否为抽象类 C 的接口
        res=B.class.isAssignableFrom(C.class);
        System.out.println("B>C:"+res);

        // 接口 B 是否为类 D 的接口
        res=B.class.isAssignableFrom(D.class);
        System.out.println("B>D:"+res);

        // 抽象类 C 是否为类 D 的父类
        res=C.class.isAssignableFrom(D.class);
        System.out.println("C>D:"+res);

        // 类 D 是否为抽象类 C 的父类
        res=D.class.isAssignableFrom(C.class);
        System.out.println("D>C:"+res);

        // 类 Boolean 是否为类 D 的父类
        res=Boolean.class.isAssignableFrom(D.class);
        System.out.println("Boolean>D:"+res);

        // 类 D 是否为类 Boolean 的父类
        res=D.class.isAssignableFrom(Boolean.class);
        System.out.println("D>Boolean:"+res);

        D d=new D("a","b","c");
    }
}
