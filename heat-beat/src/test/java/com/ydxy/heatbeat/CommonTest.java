package com.ydxy.heatbeat;

import com.ydxy.heatbeat.mapper.SystemConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @Author: huangsonglin
 * @Date:2020/7/13
 * @Description:
 */
@Slf4j
@ExtendWith(SpringExtension.class) //导入spring测试框架
@SpringBootTest
@AutoConfigureMockMvc
//mockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//可以通过@Order来定义执行顺序
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommonTest {
    @Autowired
    @BeforeEach
    public void init() {
        log.info("测试开始----------");
    }

    @AfterEach
    public void after() {
        log.info("测试结束----------");
    }

}