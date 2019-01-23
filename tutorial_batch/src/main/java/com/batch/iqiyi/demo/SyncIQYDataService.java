package com.batch.iqiyi.demo;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @Author: jimmy
 * @Date: 2019/1/23
 */
@Configuration
public class SyncIQYDataService {
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    JobBuilderFactory jobBuilderFactory;
    @Autowired
    IQYDataReaderStep iqyDataReaderStep;


    public void syncData(){
        try {
            String dateParam = new Date().toString();
            JobParameters param = new JobParametersBuilder().addString("run.time", dateParam).toJobParameters();
            Job job = jobBuilderFactory.get("iqyJob")
                    .incrementer(new RunIdIncrementer())
                    // .listener(listener())
                    .flow(iqyDataReaderStep.iqyReadStep())
                    .end()
                    .build();
            JobExecution execution = jobLauncher.run(job, param);
            System.out.println("execution:"+execution);
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }
}
