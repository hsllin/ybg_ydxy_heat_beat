package com.ydxy.heatbeat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Author: huangsonglin
 * @Date:2020/7/16
 * @Description:
 */
@Mapper
@Component
public interface ActiveMqMapper {
    /**
     * 获取数据数量
     *
     * @return
     */
    Integer getNumOfMq();

    /**
     * 清空表
     */
    void truncateTable();
}
