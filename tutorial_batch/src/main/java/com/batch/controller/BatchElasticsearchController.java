package com.batch.controller;

import com.batch.domain.Program;
import com.batch.elasticsearch.EsService;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author jimmy
 * @date 2019-02-2621:26
 */
@RestController
public class BatchElasticsearchController {
    @Autowired
    EsService esService;
    @Autowired
    JobLauncher jobLauncher;
    @Autowired
    Job esJob;

    @RequestMapping("testEs")
    public void test() throws IOException {
        List<Program> list = esService.pageProgram(0,10);
        System.out.println(list);
    }

    @RequestMapping("esJob")
    public void esJobTest() throws IOException, JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        JobExecution jobExecution = jobLauncher.run(esJob, jobParameters);
        System.out.println(jobExecution);
    }
}
