package com.ydxy.heatbeat.job;

import com.ydxy.heatbeat.service.DataService;
import com.ydxy.heatbeat.service.JobService;
import com.ydxy.heatbeat.service.TestService;
import com.ydxy.heatbeat.utils.CommandUtils;
import com.ydxy.heatbeat.utils.MessageSendUtil;
import com.ydxy.heatbeat.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


/**
 * @Author: huangsonglin
 * @Date:2020/7/10
 * @Description:
 */
@Component
@Slf4j
public class McEngineJob {
    @Autowired
    JobService jobService;
    @Autowired
    PropertyUtils propertyUtils;
    @Autowired
    MessageSendUtil messageSendUtil;
    @Autowired
    DataService dataService;
    @Autowired
    TestService testService;

    private static final int SLEEP_TIME = 60 * 1000;
    /**
     * 调度每次去查的ACTIVEMQ_MSGS里面的数量，如果连续10此都是比上一次大的话，说明阻塞了，果断重启
     */
    private static final int MAX_COUNT_NUM = 10;

    /**
     * 延迟间隔
     */
    private static final int TIME_OUT_SECONDS = 2000;
    /**
     * 上一次的调度查的ACTIVEMQ_MSGS里面的数量
     */
    private int lastActiveMqNum = 0;
    /**
     * 统计的次数
     */
    private int countNum = 0;


    /**
     * 学校名
     */
    private String schoolName;

    /**
     * 上一次发送消息的时间，避免如果工程启动失败，就一直在发邮件，就很烦
     */
    private LocalDateTime lastSendMessageTime;

    @Scheduled(cron = "${job.mcEngineJobCron}")
    public void startJob() {
        if (StringUtils.isEmpty(schoolName)) {
            initSchoolName();
        }
        String url = propertyUtils.getMcengineUrl();
        //当访问mc工程返回false表示连接不上，挂掉了，需要发送短信通知管理员，然后尝试重启
        if (!jobService.testUrlWithTimeOut(url, TIME_OUT_SECONDS)) {
            //当连不上后再尝试连接一次
            if (!jobService.testUrlWithTimeOut(url, TIME_OUT_SECONDS)) {
                String message = schoolName + "的消息工程挂了啊，过来整活" + url;
                rebootMcEngine(message);
            }
        } else {
            int currentCountMqNum = dataService.getNumOfMq();
            log.info("数据库activeMqCOunt数量:" + currentCountMqNum);
            log.info("统计次数countNum:" + countNum);
            if (currentCountMqNum >= lastActiveMqNum && currentCountMqNum > 0) {
                lastActiveMqNum = currentCountMqNum;
                countNum++;
            } else {
                //重置数量
                countNum = 0;
                lastActiveMqNum = currentCountMqNum;
            }
            if (countNum > MAX_COUNT_NUM) {
                log.info("消息发送可能被阻塞，重启工程");
                String message = schoolName + "的消息工程可能发送消息出现问题，已自动重启";
                countNum = 0;
                dataService.truncateActiveMqTable();
                rebootMcEngine(message);
            }

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
        //判断上一次发送消息的间隔，如果超过半天或者自定义的时间才发送消息，避免启动失败发消息太频繁
        if (duration.toHours() >= timeInterval) {
            messageSendUtil.sendSimpleMail(message, message);
        }

        //然后重启mcengine
        List<String> commands = CollectionUtils.arrayToList(propertyUtils.getLinuxCommands().split(","));
        for (String command : commands) {
            CommandUtils.exeCmd(command);
            try {
                log.info("执行命令：" + command);
                //过一段时间执行一次
                Thread.sleep(SLEEP_TIME);
            } catch (Exception e) {
                log.info("线程出错：" + e.getMessage());
            }
        }
    }

    private void initSchoolName() {
        schoolName = dataService.getSchoolName();
    }

//    @Scheduled(cron = "0 0/1 * * * ?")
//    public void test(){
//      TestDao testDao =  testService.getTestById("1");
//      System.out.println(testDao.getId());
//    }
}
