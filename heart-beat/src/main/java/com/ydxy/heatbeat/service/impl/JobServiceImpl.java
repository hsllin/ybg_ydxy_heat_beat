package com.ydxy.heatbeat.service.impl;

import com.ydxy.heatbeat.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: huangsonglin
 * @Date:2020/7/10
 * @Description:
 */
@Slf4j
@Component
public class JobServiceImpl implements JobService {
    @Override
    public boolean testUrlWithTimeOut(String targetUrl, int timeOutSeconds) {
        long currentTime = System.currentTimeMillis();
        URL url;
        try {
            url = new URL(targetUrl);
            URLConnection co = url.openConnection();
            co.setConnectTimeout(timeOutSeconds);
            co.connect();
            log.info(targetUrl + "连接可用");
            return true;
        } catch (Exception e1) {
            log.info(targetUrl + "连接不上!");
            url = null;
            return false;
        }
    }
}
