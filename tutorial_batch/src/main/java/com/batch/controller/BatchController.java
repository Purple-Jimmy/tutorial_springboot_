package com.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author jimmy
 * @date 2018/11/1621:32
 */
@RestController
public class BatchController {
    private String MESSAGE_FILE = "classpath:message.txt";

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job processJob;

    @Autowired
    Job messageMigrationJob;

    @RequestMapping("/invokeJob")
    public String handle() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(processJob, jobParameters);

        return "Batch job has been invoked";
    }

    @RequestMapping("/messageJob")
    public String migrateMessage() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(messageMigrationJob, jobParameters);

        return "message migrated";
    }


    @RequestMapping("/hello")
    public String hello() throws Exception {
        ClassPathResource classPathResource = new ClassPathResource("test.txt");
        System.out.println("获取文件："+classPathResource .getFile().exists());
        System.out.println("获取文件流："+ classPathResource .getInputStream().available());


        System.out.println(ResourceUtils.getFile("classpath:test.txt").exists());
        File file = new File(MESSAGE_FILE);
        System.out.println(file.exists());
        FileSystemResource fileSystemResource = new FileSystemResource(ResourceUtils.getFile(MESSAGE_FILE));
        System.out.println(fileSystemResource.exists());
        return "ss";
    }


}
