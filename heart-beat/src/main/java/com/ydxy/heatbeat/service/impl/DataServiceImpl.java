package com.ydxy.heatbeat.service.impl;

import com.ydxy.heatbeat.bean.SystemConfig;
import com.ydxy.heatbeat.mapper.ActiveMqMapper;
import com.ydxy.heatbeat.mapper.SystemConfigMapper;
import com.ydxy.heatbeat.service.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @Author: huangsonglin
 * @Date:2020/7/13
 * @Description:
 */
@Component
@Slf4j
public class DataServiceImpl implements DataService {
    @Autowired
    SystemConfigMapper systemConfigMapper;
    @Autowired
    ActiveMqMapper activeMqMapper;

    @Override
    public String getSchoolName() {
        String schoolName = "";
        SystemConfig systemConfig = null;
        try {
            systemConfig = systemConfigMapper.getSchoolName();
        } catch (Exception e) {
            log.error("请检查数据库配置是否正确:");
        }
        if (!ObjectUtils.isEmpty(systemConfig)) {
            schoolName = systemConfig.getParamName();
        }
        return schoolName;
    }

    /**
     * 获取ACTIVEMQ_MSGS的条数，如果mcengine没启动的话，可能ACTIVEMQ_MSGS不存在
     *
     * @return
     */
    @Override
    public Integer getNumOfMq() {
        Integer num = 0;
        try {
            num = activeMqMapper.getNumOfMq();
        } catch (Exception e) {
            log.error("ACTIVEMQ_MSGS表不存在：" + e.getMessage());
        }
        return num;
    }

    @Override
    public void truncateActiveMqTable() {
        activeMqMapper.truncateTable();
    }


}
