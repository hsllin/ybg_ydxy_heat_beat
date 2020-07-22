package com.ydxy.heatbeat.utils;

import com.ydxy.heatbeat.CommonTest;
import com.ydxy.heatbeat.bean.TestDao;
import com.ydxy.heatbeat.service.TestService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.omg.CORBA.COMM_FAILURE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: huangsonglin
 * @Date:2020/7/14
 * @Description:
 */

class PropertyUtilsTest extends CommonTest {
    @Autowired
    PropertyUtils propertyUtils;

    @Test
    @Order(1)
    void getMcengineUrl() {
        String url = propertyUtils.getMcengineUrl();
        Assertions.assertEquals("http://192.168.2.199:8089/mcengine/",url);
    }

    @Test
    @Order(2)
    void getToPeople() {
        String toPeople = propertyUtils.getToPeople();
        List<String> toPeopleList = CollectionUtils.arrayToList(propertyUtils.getToPeople().split(","));
        Assertions.assertEquals("15751004729@163.com,1051701859@qq.com,hsl15112580698@163.com",toPeople);
    }



    @Test
    void getUseSendMail() {
        String useSendMail = propertyUtils.getUseSendMail();
        assertEquals("true",useSendMail);
    }

    @Test
    void getLinuxCommands() {
        String commands = propertyUtils.getLinuxCommands();
        Assertions.assertEquals("service ydxymcengine stop,service ydxymcengine start",commands);
    }

}