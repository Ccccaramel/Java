package com.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LombokOfValueDemo {
    @Value("${config.level}")
    private String level;
    @Value("${config.info}")
    private String info;

    public static void main(String[] args) {
        LombokOfValueDemo lombokOfValueDemo = new LombokOfValueDemo();
        log.info("config.level:{}",lombokOfValueDemo.level);
        log.info("config.info:{}",lombokOfValueDemo.info);
    }
}