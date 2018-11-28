package com.java.thread.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author jimmy
 * @date 2018/11/2823:00
 */
public class ScheduleThreadPoolDemo {
    public static void main(String[] args) {
        //可固定长度线程池  核心线程数为3
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        for(int i=0;i<10;i++){
            final int temp = i;
            /**
             * execute方法作用:执行任务
             */
            ((ScheduledExecutorService) scheduledThreadPool).schedule(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"--"+temp);
                }
            },4, TimeUnit.SECONDS);
        }

    }
}
