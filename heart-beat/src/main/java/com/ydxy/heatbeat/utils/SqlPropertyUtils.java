package com.ydxy.heatbeat.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: huangsonglin
 * @Date:2020/7/23
 * @Description:
 */
@Component
public class SqlPropertyUtils {
    @Value("${spring.datasource.url}")
    public String sqlUrl;

    @Value("${spring.datasource.username}")
    public String sqlUSer;

    @Value("${spring.datasource.password}")
    public String sqlPassWord;

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
