package com.tutorial.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;

/**
 * Created by Jimmy. 2018/5/30  13:55
 * 发送邮件事件
 */
public class EmailRegisterEvent extends ApplicationEvent {
    private static final Logger logger = LoggerFactory.getLogger(EmailRegisterEvent.class);

    private static final String EVENT_NAME = "email_event";

    public EmailRegisterEvent(Object source) {
        super(source);
        logger.info("email event");
    }
}
