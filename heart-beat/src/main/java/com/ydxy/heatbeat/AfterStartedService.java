package com.ydxy.heatbeat;

import com.ydxy.heatbeat.service.DataService;
import com.ydxy.heatbeat.utils.PropertyUtils;
import com.ydxy.heatbeat.utils.SqlPropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author: huangsonglin
 * @Date:2020/7/23
 * @Description:
 */
@Component
@Slf4j
public class AfterStartedService implements ApplicationRunner {
    @Autowired
    SqlPropertyUtils sqlPropertyUtils;
    @Autowired
    DataService dataService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("当前学校是：" + dataService.getSchoolName());
        log.info("当前的数据库是：" + sqlPropertyUtils.getSqlUrl());
        log.info("当前的用户名是：" + sqlPropertyUtils.getSqlUSer());
        log.info("当前的密码是：" + sqlPropertyUtils.getSqlPassWord());
    }
}
