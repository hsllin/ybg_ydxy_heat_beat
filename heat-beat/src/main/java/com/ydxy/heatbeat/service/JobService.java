package com.ydxy.heatbeat.service;

import org.springframework.stereotype.Service;

/**
 * @Author: huangsonglin
 * @Date:2020/7/10
 * @Description:
 */
@Service
public interface JobService {
    /**
     * 检测url是否可以访问
     *
     * @param url
     * @param timeOutSeconds
     * @return
     */
    public boolean testUrlWithTimeOut(String url, int timeOutSeconds);
}
