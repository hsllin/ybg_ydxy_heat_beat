package com.ydxy.heatbeat.service;

import com.mysql.cj.util.LogUtils;
import com.ydxy.heatbeat.bean.HttpResult;
import com.ydxy.heatbeat.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("测试-获取数据库信息")
@Slf4j
@ExtendWith(SpringExtension.class) //导入spring测试框架
@SpringBootTest
@AutoConfigureMockMvc
        //mockMvc
class JobServiceTest {
    @Autowired
    JobService jobService;
    @Autowired
    HttpUtils httpUtils;

    @Test
    public void testConnectUrl() {
        String url = "http://222.206.86.42:8089/mcengine/";
        Map<String, Object> map = new HashMap<>();
        try {
            HttpResult httpResult = httpUtils.doPost(url, map);
            int code = httpResult.getCode();
            System.out.println(code);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

}