package com.ydxy.heatbeat.mapper;

import com.ydxy.heatbeat.bean.SystemConfig;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Author: huangsonglin
 * @Date:2020/7/13
 * @Description:
 */
@Mapper
@Component
public interface SystemConfigMapper {
    /**
     * 获取学校名称
     *
     * @return
     */
    SystemConfig getSchoolName();
}
