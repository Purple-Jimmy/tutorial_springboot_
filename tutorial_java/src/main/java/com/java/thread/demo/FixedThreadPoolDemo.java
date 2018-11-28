package com.java.thread.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jimmy
 * @date 2018/11/2822:47
 */
public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        //可固定长度线程池  核心线程数为3 即运行的线程数 最多创建3个
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for(int i=0;i<10;i++){
            final int temp = i;
            /**
             * execute方法作用:执行任务
             */
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"--"+temp);
                }
            });
        }

    }
}
