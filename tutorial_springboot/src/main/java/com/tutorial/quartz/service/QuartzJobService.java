package com.tutorial.quartz.service;

import java.util.Date;

/**
 * Created by Jimmy. 2018/4/26  17:00
 */
public interface QuartzJobService {

    /**
     * 创建Job
     * @param jobName  任务名
     * @param jobGroupName 任务组
     * @param jobClass 任务执行类
     * @param triggerName 触发器名
     * @param triggerGroupName 触发器组名
     * @param cronExpression 定时任务quartz表达式
     * @param str 自定义参数
     * @return
     */
    Date addJob(String jobName, String jobGroupName, Class jobClass, String triggerName, String triggerGroupName, String cronExpression, String str);

    /**
     * 修改Job
     * @param jobName
     * @param jobGroupName
     * @param jobClass
     * @param triggerName
     * @param triggerGroupName
     * @param cronExpression
     * @param str
     */
    void modifyJob(String jobName, String jobGroupName, Class jobClass, String triggerName, String triggerGroupName, String cronExpression, String str);


    /**
     * 删除Job
     * @param jobName
     * @param jobGroupName
     */
    void deleteJob(String jobName, String jobGroupName);

}
