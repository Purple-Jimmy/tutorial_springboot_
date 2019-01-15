package com.quartz.demo;

import lombok.Data;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by Jimmy. 2018/4/26  16:00
 */
@Data
//注释作用,当上一个任务未结束时下一个任务需进行等待
//@DisallowConcurrentExecution
public class CustomJob extends QuartzJobBean {
    private static final Logger logger = LoggerFactory.getLogger(CustomJob.class);
    /*@Autowired
    private UserService userService;*/

    private String name;

    @Override
    protected void executeInternal(JobExecutionContext ctx) throws JobExecutionException {
        System.out.println("hello,"+name);

        logger.info("hello,"+name);
       // System.out.println(userService.findById(1L));
    }
}
