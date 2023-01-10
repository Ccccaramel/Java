package com.ding.hyld;

import com.ding.hyld.utils.SpringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HyldApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(HyldApplication.class, args);
        System.out.println("***hyld***\n"+run.toString());
    }

}
