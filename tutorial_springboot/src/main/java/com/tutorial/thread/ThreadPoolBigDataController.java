package com.tutorial.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy. 2018/1/30  17:15
 */
@RestController
public class ThreadPoolBigDataController {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolBigDataController.class);
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;
    @Autowired
    private ThreadTaskService threadTaskService;

    @RequestMapping("/bigData")
    public String  bigData(){
        Long start = System.currentTimeMillis();
        logger.info("big data start..");
        //  final Lock lock = new ReentrantLock();
        for(int i=1;i<500;i++){
            final int num = i;
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000*3);
                        logger.info(Thread.currentThread().getName()+"--执行"+num+"--active:"+taskExecutor.getActiveCount()+"--poolSize:"+taskExecutor.getPoolSize());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        logger.info("end "+(System.currentTimeMillis()-start));
        return "success";
    }


    @RequestMapping("/asyncTask")
    public String  asyncTask() throws Exception {
        threadTaskService.doTaskOne();;
        threadTaskService.doTaskTwo();
        threadTaskService.doTaskThree();
        return "success";
    }
}
