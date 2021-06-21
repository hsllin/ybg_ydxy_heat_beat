package com.ydxy.heatbeat.service.impl;

import com.ydxy.heatbeat.bean.HttpResult;
import com.ydxy.heatbeat.service.JobService;
import com.ydxy.heatbeat.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: huangsonglin
 * @Date:2020/7/10
 * @Description:
 */
@Slf4j
@Component
public class JobServiceImpl implements JobService {
    private final static int OK_CODE=200;
    @Autowired
    HttpUtils httpUtils;
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

    @Override
    public boolean judgeUrlIsActive(String url) {
        Map<String, Object> map = new HashMap<>();
        boolean result = false;
        try {
            HttpResult httpResult = httpUtils.doPost(url, map);
            int code = httpResult.getCode();
            log.info("访问" + url + " 状态码为：" + code);
            if (OK_CODE == code) {
                result = true;
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            return false;
        }
        return result;
    }

}
