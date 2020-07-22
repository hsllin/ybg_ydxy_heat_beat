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
    Integer getNumOfMq();

   void truncateTable();
}
