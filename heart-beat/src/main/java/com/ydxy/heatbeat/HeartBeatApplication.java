package com.ydxy.heatbeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author: huangsonglin
 * @Date:2020/7/10
 * @Description: 启动类
 */
@SpringBootApplication
@EnableScheduling
public class HeartBeatApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeartBeatApplication.class, args);
    }

}
