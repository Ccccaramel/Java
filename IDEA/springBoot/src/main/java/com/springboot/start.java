package com.springboot;

import com.springboot.bean.Teacher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 声明该应用是一个 springBoot 应用
 */
@SpringBootApplication
public class start {
    public static void main(String[] args){
        ConfigurableApplicationContext run = SpringApplication.run(start.class, args);
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println("name:"+name);
        }
        // 获取组件
        System.out.println("teacher1:"+run.containsBean("teacher1"));

        // 获取 beans.xml
        System.out.println("beanTea:"+run.containsBean("beanTea"));
    }
}
