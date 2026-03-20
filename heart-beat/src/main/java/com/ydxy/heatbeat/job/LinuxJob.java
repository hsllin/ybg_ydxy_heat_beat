package com.ydxy.heatbeat.job;

import com.alibaba.druid.util.StringUtils;
import com.ydxy.heatbeat.service.DataService;
import com.ydxy.heatbeat.service.LinuxService;
import com.ydxy.heatbeat.utils.CommandUtils;
import com.ydxy.heatbeat.utils.DingTalkUtils;
import com.ydxy.heatbeat.utils.MessageSendUtil;
import com.ydxy.heatbeat.utils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 佛祖保佑此代码没有bug，即使有也一眼看出
 *
 * @author lin
 * @Description
 * @create 2025-04-09 18:00
 */
@Component
public class LinuxJob {
    private static final Logger log = LoggerFactory.getLogger(com.ydxy.heatbeat.job.LinuxJob.class);

    @Autowired
    LinuxService linuxService;

    @Autowired
    PropertyUtils propertyUtils;

    @Autowired
    MessageSendUtil messageSendUtil;

    @Autowired
    DataService dataService;

    private static final int SLEEP_TIME = 60000;

    private static final int MAX_COUNT_NUM = 3;

    private static final int TIME_OUT_SECONDS = 2000;

    private int alertNum = 50;

    private int countNum = 0;

    private boolean isFirstStart = true;

    private String schoolName;

    private LocalDateTime lastSendMessageTime = LocalDateTime.now();

    @Scheduled(cron = "${job.linuxJobCron}")
    public void startJob() {
        if ("0".equals(this.propertyUtils.getNeedObserveCloseWait()))
            return;
        if (StringUtils.isEmpty(this.schoolName))
            initSchoolName();
        String url = this.propertyUtils.getMcengineUrl();
        int currentCountCloseWaitNum = this.linuxService.judgeCloseWait().intValue();
        log.info("服务器closeWait数量:" + currentCountCloseWaitNum);
        log.info("统计次数countNum:" + countNum);
        log.info("getCloseWaitNum:" + propertyUtils.getCloseWaitNum());
        if (currentCountCloseWaitNum >= Integer.parseInt(propertyUtils.getCloseWaitNum()) && currentCountCloseWaitNum > 0) {
            this.countNum++;
        } else {
            this.countNum = 0;
        }
        if (this.countNum > MAX_COUNT_NUM) {

            String message = this.schoolName + "的close wait统计数量连续" + '\003' + "次只增不减，可能出现问题。当前close wait数量：" + currentCountCloseWaitNum;
            this.countNum = 0;
            sendCloseWaitMessage(message);
            rebootMcEngine(message);
        }
    }

    public void sendCloseWaitMessage(String message) {
        LocalDateTime currentTime = LocalDateTime.now();
        Duration duration = Duration.between(this.lastSendMessageTime, currentTime);
        long timeInterval = Long.parseLong(this.propertyUtils.getTimeInterVal());
        if (this.isFirstStart || duration.toHours() >= timeInterval) {
            this.lastSendMessageTime = currentTime;
            this.isFirstStart = false;
            log.info("close wait统计服务:" + message);
            ExecutorService executor = Executors.newFixedThreadPool(2);
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> DingTalkUtils.sendMessageByText("消息工程服务:" + message, new ArrayList(), true), executor);
            this.messageSendUtil.sendSimpleMail(message, message);
        }
    }

    /**
     * 重启mcengine工程，并发邮件通知
     *
     * @param message
     */
    public void rebootMcEngine(String message) {
        LocalDateTime currentTime = LocalDateTime.now();

        Duration duration = Duration.between(lastSendMessageTime, currentTime);
        long timeInterval = Long.parseLong(propertyUtils.getTimeInterVal());

        //然后重启mcengine
        List<String> commands = CollectionUtils.arrayToList(propertyUtils.getUrpCommands().split(","));
        for (String command : commands) {
            try {
                CommandUtils.exeCmd(command);
                log.info("执行命令：" + command);
                //过一段时间执行一次
                Thread.sleep(SLEEP_TIME);
            } catch (Exception e) {
                log.info("线程出错：" + e.getMessage());
            }
        }
    }

    private void initSchoolName() {
        this.schoolName = this.dataService.getSchoolName();
    }
}