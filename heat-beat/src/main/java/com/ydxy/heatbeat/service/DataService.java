package com.ydxy.heatbeat.service;

import org.springframework.stereotype.Service;

/**
 * @Author: huangsonglin
 * @Date:2020/7/13
 * @Description:
 */
@Service
public interface DataService {
    /**
     * 获取学校名
     *
     * @return
     */
    String getSchoolName();

    /**
     * @return 获取ACTIVEMQ_MSGS这个表的消息条数
     */
    Integer getNumOfMq();

    /**
     * 清除ACTIVEMQ_MSGS全部数据
     */
    void truncateActiveMqTable();
}
