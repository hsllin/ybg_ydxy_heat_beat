package com.ydxy.heatbeat.service.impl;

import com.ydxy.heatbeat.service.LinuxService;
import com.ydxy.heatbeat.utils.CommandUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 佛祖保佑此代码没有bug，即使有也一眼看出
 *
 * @author lin
 * @Description
 * @create 2025-04-09 17:59
 */
@Component
public class LinuxServiceImpl implements LinuxService {
    private static final Logger log = LoggerFactory.getLogger(com.ydxy.heatbeat.service.impl.LinuxServiceImpl.class);

    public Integer judgeCloseWait() {
        String linuxCommand = "netstat -n|grep  ^tcp|awk '{print $NF}'|sort -nr|uniq -c";
        String s = CommandUtils.exec(linuxCommand);
        String[] arr = s.split("\\s+");
        List<String> numList = new ArrayList<>();
        List<String> paramList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) {
                paramList.add(arr[i]);
            } else {
                numList.add(arr[i]);
            }
        }
        StringBuilder sb = (new StringBuilder(64)).append(paramList.toString()).append("\n").append("-------------------------------\n").append(numList.toString());
        if (((String)paramList.get(paramList.size() - 1)).equals("CLOSE_WAIT"))
            return Integer.valueOf(Integer.parseInt(numList.get(numList.size() - 1)));
        return Integer.valueOf(0);
    }
}