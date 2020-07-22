package com.ydxy.heatbeat.service;

import com.ydxy.heatbeat.mapper.SystemConfigMapper;
import com.ydxy.heatbeat.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: huangsonglin
 * @Date:2020/7/13
 * @Description:
 */
@DisplayName("测试-获取数据库信息")
@Slf4j
@ExtendWith(SpringExtension.class) //导入spring测试框架
@SpringBootTest
@AutoConfigureMockMvc //mockMvc
class DataServiceTest {
    @Autowired
    SystemConfigMapper systemConfigMapper;
    @Autowired
    DataService dataService;
    @Autowired
    PropertyUtils propertyUtils;


    @Test
    void getSchoolName() {
        String schoolName = systemConfigMapper.getSchoolName().getParamName();
        Assertions.assertTrue(schoolName.equalsIgnoreCase("奕学院"));
    }
    @Test
    void getNumOfMq(){
        int num =dataService.getNumOfMq();
        assertEquals(1,num);
    }
}