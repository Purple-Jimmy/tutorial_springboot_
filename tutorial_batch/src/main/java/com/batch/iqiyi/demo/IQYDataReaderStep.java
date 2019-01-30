package com.batch.iqiyi.demo;

import com.batch.step.Writer;
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
    IQYProcessorListener iqyProcessorListener;
    @Autowired
    IQYWriteListener iqyWriteListener;
    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    public Step iqyReadStep() {
        log.info("iqyReadStep start...");
        Step step = stepBuilderFactory.get("iqyReadStep")
                .<IQYDomain, IQYWriterDomain>chunk(3)
                .faultTolerant()
               /* .skip(JsonSyntaxException.class)
                .skip(FlatFileParseException.class)
                .skip(MalformedJsonException.class)*/
                .skip(Exception.class)
                .skipLimit(2)
                .reader(iqyReadHelper.syncReader())
                .listener(new IQYReadListener(new Writer()))
                .processor(iqyProcessor)
                .listener(iqyProcessorListener)
                .writer(iqyWriteHelper.syncWriter())
               // .taskExecutor(taskExecutor)
                .listener(iqyWriteListener)
                .build();
        return step;
    }


}
