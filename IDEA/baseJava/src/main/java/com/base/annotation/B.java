package com.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE,ElementType.METHOD})  // 有效范围
@Retention(RetentionPolicy.RUNTIME)  // 生命周期
public @interface B {
    String value() default "hi!";  // 特殊属性,如果只有一个 value 属性,使用注解时名称可以不写
}
