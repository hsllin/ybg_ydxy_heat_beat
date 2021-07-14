package com.ydxy.heatbeat.utils;

import com.alibaba.druid.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: huangsonglin
 * @Date:2020/7/10
 * @Description:发送消息服务
 */
@Component
@Slf4j
public class MessageSendUtil {

    /**
     * JavaMailSender 在Mail 自动配置类 MailSenderAutoConfiguration 中已经导入，这里直接注入使用即可
     */
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    PropertyUtils propertyUtils;

    private static final String FROM = "hsl15112580698@163.com";
    private static final String CC = "1051701859@qq.com";
    private static final String IS_ON_SEND_MESSAGE = "true";

    /**
     * 方法5个参数分别表示：邮件发送者、收件人、抄送人、邮件主题以及邮件内容
     *
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String subject, String content) {
        // 简单邮件直接构建一个 SimpleMailMessage 对象进行配置并发送即可
        List<String> toPeople = CollectionUtils.arrayToList(propertyUtils.getToPeople().split(","));
        //开启了邮件推送才推送，默认开启
        String useSendMail = propertyUtils.getUseSendMail();
        if (StringUtils.equalsIgnoreCase(useSendMail, IS_ON_SEND_MESSAGE)) {
            try {
                for (String toPerson : toPeople) {
                    SimpleMailMessage simpMsg = new SimpleMailMessage();
                    simpMsg.setFrom(FROM);
                    simpMsg.setTo(toPerson);
                    simpMsg.setCc(CC);
                    simpMsg.setSubject(subject);
                    simpMsg.setText(content);
                    javaMailSender.send(simpMsg);
                }
            } catch (Exception e) {
                log.info("发送邮件出错：" + e.getMessage());
            }
        }
    }
}
