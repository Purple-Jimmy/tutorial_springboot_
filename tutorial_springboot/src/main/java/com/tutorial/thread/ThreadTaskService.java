package com.tutorial.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Random;

/**
 * Created by Jimmy. 2018/4/2  10:17
 */
@Component
public class ThreadTaskService {
    private Logger LOGGER = LoggerFactory.getLogger(ThreadTaskService.class);
    public static Random random = new Random();

    @Async("taskExecutor")
    public void doTaskOne() throws Exception {
        LOGGER.info("开始做任务一");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(random.nextInt(10000));
        stopWatch.stop();
        LOGGER.info("完成任务一,耗时:" + stopWatch.getTotalTimeMillis() + "毫秒");
    }

    @Async("taskExecutor")
    public void doTaskTwo() throws Exception {
        LOGGER.info("开始做任务二");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(random.nextInt(10000));
        stopWatch.stop();
        LOGGER.info("完成任务二,耗时:" + stopWatch.getTotalTimeMillis() + "毫秒");
    }

    @Async("taskExecutor")
    public void doTaskThree() throws Exception {
        LOGGER.info("开始做任务三");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(random.nextInt(10000));
        stopWatch.stop();
        LOGGER.info("完成任务三,耗时:" + stopWatch.getTotalTimeMillis() + "毫秒");
    }
}
