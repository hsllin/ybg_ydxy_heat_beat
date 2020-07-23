package com.ydxy.heatbeat.controller;

import com.ydxy.heatbeat.CommonTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: huangsonglin
 * @Date:2020/7/14
 * @Description:
 */
@Slf4j
class TestControllerTest extends CommonTest {
@Autowired
    MockMvc mockMvc;

    @Test
    void hello() throws Exception{
        RequestBuilder req = MockMvcRequestBuilders.get("/my/hello");
        MvcResult result = mockMvc.perform(req).andReturn();
        int httpStatus = result.getResponse().getStatus();
        String content = result.getResponse().getContentAsString();
        assertEquals("hello my love",content);
        log.info("Response: HttpStatus={},content={}", httpStatus, content);
        Assertions.assertTrue(httpStatus == HttpStatus.OK.value());
    }

    @Test
    void sendMessage() {
    }
}