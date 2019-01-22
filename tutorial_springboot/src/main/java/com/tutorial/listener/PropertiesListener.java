package com.tutorial.listener;

import com.tutorial.configurer.PropertiesLoadConfigurer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Author: jimmy
 * @Date: 2019/1/22
 * springboot2.0 事件
 * ApplicationStartingEvent
 * ApplicationEnvironmentPreparedEvent
 * ApplicationPreparedEvent
 * ApplicationStartedEvent <= 新增的事件
 * ApplicationReadyEvent
 * ApplicationFailedEvent
 */
@Slf4j
@Component
public class PropertiesListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        log.info("load categoryCode.properties start....");
        PropertiesLoadConfigurer.loadAllProperties("categoryCode.properties");
        System.out.println(PropertiesLoadConfigurer.getAllProperty().size()+"-==========");
    }
}
