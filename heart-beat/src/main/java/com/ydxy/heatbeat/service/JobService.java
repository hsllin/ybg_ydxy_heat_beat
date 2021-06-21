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
     * 检测url是否可以访问,经过测试有问题，已废弃
     *
     * @param url
     * @param timeOutSeconds
     * @return
     */
    public boolean testUrlWithTimeOut(String url, int timeOutSeconds);

    /**
     * 判断消息工程的url是否可以访问，新的方法，通过接口访问
     *
     * @param url
     * @return
     */
    public boolean judgeUrlIsActive(String url);
}
