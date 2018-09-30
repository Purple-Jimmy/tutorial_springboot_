package com.tutorial.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by Jimmy. 2018/5/30  10:28
 * 注册事件 实现ApplicationListener方式
 */
@Component
public class EmailListener implements ApplicationListener<EmailRegisterEvent> {

    /**
     * 实现监听
     * @param emailRegisterEvent
     */
    @Override
    public void onApplicationEvent(EmailRegisterEvent emailRegisterEvent) {
        System.out.println("监听 email event");
    }
}
