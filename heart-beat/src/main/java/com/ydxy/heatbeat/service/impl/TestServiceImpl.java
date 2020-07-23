package com.ydxy.heatbeat.service.impl;

import com.ydxy.heatbeat.bean.TestDao;
import com.ydxy.heatbeat.mapper.TestMapper;
import com.ydxy.heatbeat.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: huangsonglin
 * @Date:2020/7/10
 * @Description:
 */
@Component
public class TestServiceImpl implements TestService {
    @Autowired
    TestMapper testMapper;
    @Override
    public TestDao getTestById(String id) {
        return testMapper.findById(id);
    }
}
