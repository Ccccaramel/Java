package com.ding.hyld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableAsync
@Slf4j
@EnableFeignClients
@SpringBootApplication
public class HyldApplication {

    public static void main(String[] args) {
        SpringApplication.run(HyldApplication.class, args);
        log.info("\n"+
                "       __   _    __    __   ___  ___  __   __  ___\n"+
                "      (  ) / )  / ,)  /  \\ (  _)(  _)(  ) / _)(  _)\n"+
                "       )( / , \\(_  _)( () ) ) _) ) _) )( ( (_  ) _)\n"+
                "      (__)\\___/  (_)  \\__/ (_)  (_)  (__) \\__)(___)\n"+
                "                 芽芽office & 164office.cn\n"); // http://patorjk.com/software/taag/ Serifcap
//        System.out.println("\n"+
//                "       __   _    __    __   ___  ___  __   __  ___\n"+
//                "      (  ) / )  / ,)  /  \\ (  _)(  _)(  ) / _)(  _)\n"+
//                "       )( / , \\(_  _)( () ) ) _) ) _) )( ( (_  ) _)\n"+
//                "      (__)\\___/  (_)  \\__/ (_)  (_)  (__) \\__)(___)\n"+
//                "                 芽芽office & 164office.cn\n"); // http://patorjk.com/software/taag/ Serifcap
    }
}