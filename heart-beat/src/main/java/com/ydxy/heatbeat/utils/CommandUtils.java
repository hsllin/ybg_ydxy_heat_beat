package com.ydxy.heatbeat.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: huangsonglin
 * @Date:2020/7/13
 * @Description:在linux中调用命令
 */
public class CommandUtils {
    /**
     * 输入linux命令
     *
     * @param commandStr
     */
    public static void exeCmd(String commandStr) {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        String commandStr = "ps -ef|grep urp";
        //String commandStr = "ipconfig";
        CommandUtils.exeCmd(commandStr);
    }

}
