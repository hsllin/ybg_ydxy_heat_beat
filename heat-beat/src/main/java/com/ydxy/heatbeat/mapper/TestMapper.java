package com.ydxy.heatbeat.mapper;

import com.ydxy.heatbeat.bean.TestDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Author: huangsonglin
 * @Date:2020/7/10
 * @Description:
 */
@Component
@Mapper
public interface TestMapper {
    /**
     * 测试用
     *
     * @param id
     * @return
     */
    TestDao findById(String id);
}
