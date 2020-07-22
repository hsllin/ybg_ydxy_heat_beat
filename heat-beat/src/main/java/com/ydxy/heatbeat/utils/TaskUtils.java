package com.ydxy.heatbeat.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * @Author: huangsonglin
 * @Date:2020/7/13
 * @Description:用于执行延迟任务
 */
public class TaskUtils {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
        LocalDateTime sencon = LocalDateTime.now().plusDays(1);;
        LocalDateTime third = LocalDateTime.now();
        try{
        Thread.sleep(3000);}
        catch (Exception e){}
        Duration duration = Duration.between(third,sencon);
        System.out.println(sencon);
        System.out.println(duration.toHours());
    }

}
