package com.base.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@A(say = "hi!", score = {1,2,3})
public class AnnotationDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c= AnnotationDemo.class;
        System.out.println("--- 对象上的注解 ---");
        if (c.isAnnotationPresent(A.class)){  // 判断当前对象上是否存在某个注解
            A annotation = (A)c.getDeclaredAnnotation(A.class);  // 获取对象上面的注解
            System.out.println("A.say:"+annotation.say());
            System.out.println("A.score:"+ Arrays.toString(annotation.score()));
        }

        System.out.println("--- 方法上的注解 ---");
        Method method = c.getDeclaredMethod("fun1");  // 获取指定方法上的注解对象
        if (method.isAnnotationPresent(A.class)){
            A annotation = (A)method.getDeclaredAnnotation(A.class);
            System.out.println("A.say:"+annotation.say());
            System.out.println("A.score:"+ Arrays.toString(annotation.score()));
        }

        System.out.println("--- 模拟 Junit 框架 ---");
        AnnotationDemo annotationDemo =new AnnotationDemo();
        for (Method cMethod : c.getMethods()) {
            if(cMethod.isAnnotationPresent(B.class)){
                cMethod.invoke(annotationDemo);
            }
        }
    }

    @A(say = "hello!", score = {6,6,6})
    public void fun1(){
    }

    @B("2333!")
    public void fun2(){
        System.out.println("---2---");
    }

    public void fun3(){
        System.out.println("---3---");
    }

    @B(value = "111!")
    public void fun4(){
        System.out.println("---4---");
    }
}
