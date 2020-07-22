package com.ydxy.heatbeat.service;

import com.ydxy.heatbeat.bean.TestDao;
import org.springframework.stereotype.Service;

/**
 * @Author: huangsonglin
 * @Date:2020/7/10
 * @Description:
 */
@Service
public interface TestService {

    /**
     * 测试用
     *
     * @param id
     * @return
     */
    TestDao getTestById(String id);
}
