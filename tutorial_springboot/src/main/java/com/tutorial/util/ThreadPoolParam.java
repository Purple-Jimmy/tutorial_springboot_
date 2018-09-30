package com.tutorial.util;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by Jimmy. 2018/1/30  17:12
 */
@Data
@Component
@ConfigurationProperties(prefix="spring.task.pool")
@PropertySource("classpath:/data-binder.properties")
public class ThreadPoolParam {
    private int corePoolSize;
    private int maxPoolSize;
    private int keepAliveSeconds;
    private int queueCapacity;
}