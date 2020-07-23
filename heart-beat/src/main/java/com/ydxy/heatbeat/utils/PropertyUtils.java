package com.ydxy.heatbeat.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: huangsonglin
 * @Date:2020/7/10
 * @Description:
 */
@ConfigurationProperties(prefix = "myproperty")
@Component
public class PropertyUtils {

    /**
     * 要检测的mc工程的地址
     */
    @Value("mcengineUrl")
    private String mcengineUrl;

    /**
     * 邮件接收人的邮箱地址
     */
    @Value("toPeople")
    public String toPeople;

    /**
     * 是否开启邮件推送
     */
    @Value("useSendMail")
    public String useSendMail;

    @Value("linuxCommands")
    public String linuxCommands;
    /**
     * 发送邮件的时间间隔，单位是小时，默认为12小时，即半天
     */
    @Value("timeInterVal")
    public String timeInterVal;

    @Value("spring.datasource.url")
    public String sqlUrl;

    @Value("spring.datasource.username")
    public String sqlUSer;

    @Value("spring.datasource.password")
    public String sqlPassWord;


    public String getMcengineUrl() {
        return mcengineUrl;
    }

    public void setMcengineUrl(String mcengineUrl) {
        this.mcengineUrl = mcengineUrl;
    }

    public String getToPeople() {
        return toPeople;
    }

    public void setToPeople(String toPeople) {
        this.toPeople = toPeople;
    }


    public String getUseSendMail() {
        return useSendMail;
    }

    public void setUseSendMail(String useSendMail) {
        this.useSendMail = useSendMail;
    }

    public String getLinuxCommands() {
        return linuxCommands;
    }

    public void setLinuxCommands(String linuxCommands) {
        this.linuxCommands = linuxCommands;
    }

    public String getTimeInterVal() {
        return timeInterVal;
    }

    public void setTimeInterVal(String timeInterVal) {
        this.timeInterVal = timeInterVal;
    }

    public String getSqlUrl() {
        return sqlUrl;
    }

    public void setSqlUrl(String sqlUrl) {
        this.sqlUrl = sqlUrl;
    }

    public String getSqlUSer() {
        return sqlUSer;
    }

    public void setSqlUSer(String sqlUSer) {
        this.sqlUSer = sqlUSer;
    }

    public String getSqlPassWord() {
        return sqlPassWord;
    }

    public void setSqlPassWord(String sqlPassWord) {
        this.sqlPassWord = sqlPassWord;
    }
}
