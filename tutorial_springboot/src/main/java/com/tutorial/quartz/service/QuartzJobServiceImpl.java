package com.tutorial.quartz.service;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Jimmy. 2018/4/26  17:02
 */
@Service
public class QuartzJobServiceImpl implements QuartzJobService {
    private static final Logger logger = LoggerFactory.getLogger(QuartzJobServiceImpl.class);
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Override
    public Date addJob(String jobName, String jobGroupName, Class jobClass, String triggerName, String triggerGroupName, String cronExpression,String str) {
        Scheduler scheduler =  schedulerFactoryBean.getScheduler();

        //job定义:任务执行类 任务名 任务组
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).usingJobData("name",str).build();

        //触发器构建
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
        try {
            return scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            logger.info("创建 jobName:{} jobGroupName:{}的Job error",jobName,jobGroupName,e);
            return null;
        }
    }

    @Override
    public void modifyJob(String jobName, String jobGroupName, Class jobClass, String triggerName, String triggerGroupName, String cronExpression, String str) {
        Scheduler scheduler =  schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(jobName,jobGroupName);
        try{
            if(!scheduler.checkExists(jobKey)){
                logger.info("不存在jobName:{} jobGroupName:{}的Job",jobName,jobGroupName);
                //return;
            }else{
                //删除Job
                scheduler.deleteJob(jobKey);
            }
            //job定义:任务执行类 任务名 任务组
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).usingJobData("name",str).build();
            //触发器构建
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
            logger.info("修改 jobName:{} jobGroupName:{} triggerName:{} triggerGroupName:{}的Job",jobName,jobGroupName,triggerName,triggerGroupName);
            scheduler.scheduleJob(jobDetail, trigger);
        }catch(SchedulerException e){
            logger.info("修改 jobName:{} jobGroupName:{}的Job error",jobName,jobGroupName,e);
        }
    }

    @Override
    public void deleteJob(String jobName, String jobGroupName){
        Scheduler scheduler =  schedulerFactoryBean.getScheduler();
        JobKey jobKey = JobKey.jobKey(jobName,jobGroupName);
        try{
            if(scheduler.checkExists(jobKey)){
                logger.info("存在jobName:{} jobGroupName:{}的Job",jobName,jobGroupName);
                scheduler.deleteJob(jobKey);
                logger.info("删除jobName:{} jobGroupName:{}的Job",jobName,jobGroupName);
            }else{
                logger.info("不存在jobName:{} jobGroupName:{}的Job",jobName,jobGroupName);
            }
        }catch(SchedulerException e){
                logger.info("删除 jobName:{} jobGroupName:{}的Job error",jobName,jobGroupName,e);
        }
    }
}
