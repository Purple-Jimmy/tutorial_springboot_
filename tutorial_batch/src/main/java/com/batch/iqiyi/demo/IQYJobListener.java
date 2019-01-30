package com.batch.iqiyi.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @Author: jimmy
 * @Date: 2019/1/23
 */
@Component
@Slf4j
public class IQYJobListener implements JobExecutionListener {
    StopWatch stopWatch = new StopWatch();
    @Override
    public void beforeJob(JobExecution jobExecution) {
        stopWatch.start();
        log.info("IQYJobListener start execute params:{}", jobExecution.getJobParameters());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("iqiyi execute finished");
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            log.info("iqiyi execute failed");
        }
        stopWatch.stop();
        log.info("iqiyi execute cost Time : {}ms", stopWatch.getTotalTimeMillis());
    }
}
