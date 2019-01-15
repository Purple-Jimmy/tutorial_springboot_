package com.quartz.demo;

import org.quartz.*;
import org.springframework.context.annotation.Bean;

/**
 * Created by Jimmy. 2018/4/26  16:01
 */
//@Configuration
public class CustomJobConfigurer {
    @Bean
    public JobDetail customJobDetail() {
        return JobBuilder.newJob(CustomJob.class).withIdentity("HelloJob")
                .usingJobData("name","Jimmy")
                .storeDurably().build();
    }

    @Bean
    public Trigger customJobTrigger() {
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever();
        return TriggerBuilder.newTrigger().forJob(customJobDetail()).withIdentity("HelloTrigger").withSchedule(simpleScheduleBuilder).build();
    }
}
