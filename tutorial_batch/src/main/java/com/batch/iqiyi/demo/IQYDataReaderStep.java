package com.batch.iqiyi.demo;

import com.batch.step.Writer;
import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * @Author: jimmy
 * @Date: 2019/1/23
 */
@Component
@Slf4j
public class IQYDataReaderStep {
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    IQYReadHelper iqyReadHelper;
    @Autowired
    IQYWriteHelper iqyWriteHelper;
    @Autowired
    IQYProcessor iqyProcessor;
    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    public Step iqyReadStep() {
        log.info("iqyReadStep start...");
        Step step = stepBuilderFactory.get("iqyReadStep")
                .<IQYDomain, IQYDomain>chunk(5)
                .reader(iqyReadHelper.syncReader()).faultTolerant().skip(JsonParseException.class).skipLimit(4)
                .listener(new IQYReadListener(new Writer()))
                .processor(iqyProcessor)
                .writer(iqyWriteHelper).faultTolerant().skip(Exception.class).skipLimit(4)
                .taskExecutor(taskExecutor)
                .listener(new IQYWriteListener())
                .build();
        return step;
    }


}
