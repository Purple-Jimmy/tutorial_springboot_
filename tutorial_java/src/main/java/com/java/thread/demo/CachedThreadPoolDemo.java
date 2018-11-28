package com.java.thread.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jimmy
 * @date 2018/11/2822:33
 * 可缓存 定长 定时 单例
 */
public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            final int temp = i;
            /**
             * execute方法作用:执行任务
             */
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"--"+temp);
                }
            });
        }

    }
}
