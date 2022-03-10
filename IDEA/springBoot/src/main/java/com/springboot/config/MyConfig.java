package com.springboot.config;

import com.springboot.bean.Car;
import com.springboot.bean.Student;
import com.springboot.bean.Teacher;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

// 配置类,本质也是组件
/**
 * @Configuration
 *   proxyBeanMethods : 是否代理 bean 方法,保持组件单实例
 *     Full 模式:组件间有依赖关系可设置为 true
 *     Lite 模式:组件间无依赖关系可设置为 fasle
 **/
@Configuration(proxyBeanMethods = true)
/**
 * @("classpath:beans.xml")
 *   要想实现 beans.xml 配置文件在 IOC 容器中获取
 *     1.转换成注解
 *     2.使用 ImportResource 解析并放入 IOC 容器
 */
@ImportResource("classpath:beans.xml")
/**
 * @EnableConfigurationProperties(Car.class)
 * 开启 Car 属性配置绑定功能,把该组件自动注册到容器中
 * 这样就可以代替
 *   @Component
 * 可以将第三方包导入进来
 */
@EnableConfigurationProperties(Car.class)
public class MyConfig {

    // 单实例
    @Bean
    public Teacher teacher1(){
        Teacher teacher = new Teacher("En", "119");
        teacher.setStudent(student1());
        return teacher;
    }

    // 默认组件名为方法名,可以自定义组件名,替代方法名
    @Bean("stu1")
    public Student student1(){
        return new Student("Yong",22);
    }
}
