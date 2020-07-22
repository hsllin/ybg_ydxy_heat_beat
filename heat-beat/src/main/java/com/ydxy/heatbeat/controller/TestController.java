package com.ydxy.heatbeat.controller;

import com.ydxy.heatbeat.bean.TestDao;
import com.ydxy.heatbeat.service.DataService;
import com.ydxy.heatbeat.service.TestService;
import com.ydxy.heatbeat.utils.MessageSendUtil;
import com.ydxy.heatbeat.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: huangsonglin
 * @Date:2020/7/10
 * @Description:
 */
@RestController
@RequestMapping("/my")
@Slf4j
public class TestController {
    @Autowired
    TestService testService;
    @Autowired
    MessageSendUtil messageSendUtil;
    @Autowired
    DataService dataService;
    @Autowired
    PropertyUtils propertyUtils;

    @RequestMapping(value = "/hello")
    public String hello() {
        TestDao testDao = testService.getTestById("1");
        return "hello my love";
    }

    @RequestMapping(value = "/send")
    public void sendMessage() {
        String content="测试主题";
        String subject ="xx学校名称工程崩溃了，请过来临幸一下";

        List<String> commands = CollectionUtils.arrayToList(propertyUtils.getLinuxCommands().split(","));
        log.info("xuexxiao:"+commands.toString());
//        messageSendUtil.sendSimpleMail(subject,content);
    }
}
