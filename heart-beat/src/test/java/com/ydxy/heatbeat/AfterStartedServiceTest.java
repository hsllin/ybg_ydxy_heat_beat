package com.ydxy.heatbeat;

import com.ydxy.heatbeat.service.DataService;
import com.ydxy.heatbeat.utils.SqlPropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.DatagramSocket;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: huangsonglin
 * @Date:2020/7/23
 * @Description:
 */
@Slf4j
class AfterStartedServiceTest extends CommonTest{
    @Autowired
    SqlPropertyUtils sqlPropertyUtils;
    @Autowired
    DataService dataService;

    @Test
    void run() {
        assertEquals("奕学院",dataService.getSchoolName());
        assertEquals("=jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.2.37)(PORT = 1521))    (CONNECT_DATA =      (SERVER = DEDICATED)      (SERVICE_NAME = ORCL)    ))\n" +
                "spring.datasource.url=jdbc:oracle:thin:@192.168.2.37:1521:orcl",sqlPropertyUtils.getSqlUrl());
        assertEquals("YBG_CP",sqlPropertyUtils.getSqlPassWord());
        assertEquals("YBG_CP",sqlPropertyUtils.getSqlUSer());
        log.info("当前学校是：" + dataService.getSchoolName());
        log.info("当前的数据库是：" + sqlPropertyUtils.getSqlUrl());
        log.info("当前的用户名是：" + sqlPropertyUtils.getSqlUSer());
        log.info("当前的密码是：" + sqlPropertyUtils.getSqlPassWord());
    }
}