package com.ydxy.heatbeat.service;

import com.ydxy.heatbeat.bean.TestDao;
import com.ydxy.heatbeat.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: huangsonglin
 * @Date:2020/7/10
 * @Description:
 */
@Service
public interface TestService {

    public TestDao getTestById(String id);
}
