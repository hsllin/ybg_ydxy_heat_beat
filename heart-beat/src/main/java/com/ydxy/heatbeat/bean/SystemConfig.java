package com.ydxy.heatbeat.bean;

/**
 * @Author: huangsonglin
 * @Date:2020/7/13
 * @Description:
 */
public class SystemConfig {
    private String paramKey;
    private String paramName;
    private String description;

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
