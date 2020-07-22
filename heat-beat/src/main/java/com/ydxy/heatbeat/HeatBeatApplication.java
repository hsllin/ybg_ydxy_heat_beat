package com.ydxy.heatbeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HeatBeatApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeatBeatApplication.class, args);
    }

}
