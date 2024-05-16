package com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 声明该应用是一个 springBoot 应用
 */
//@ServletComponentScan
@SpringBootApplication
public class start {
    public static void main(String[] args){
        ConfigurableApplicationContext run = SpringApplication.run(start.class, args);
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("name:"+name);
        }

        // 判断指定名称的组件是否已注册到容器中
        System.out.println("filter:"+run.containsBean("CommonFilter"));
    }
}
