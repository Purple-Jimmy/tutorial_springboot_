package com.batch.configurer;

import com.batch.listener.JobCompleteListener;
import com.batch.step.Processor;
import com.batch.step.Reader;
import com.batch.step.Writer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jimmy
 * @date 2018/11/1621:12
 */
@Configuration
public class BatchSampleConfigurer {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    /**
     * JobParameters 相同的任务只能成功运行一次
     * 如果希望该任务是周期执行的,那么必须保证周期内参数是唯一
     * 因此配置Job时需要使用incrementer方法为每次执行创建一个递增的ID保证唯一性,否则任务仅会执行一次
     * @return
     * processJob 定义job的名字
     */
    @Bean
    public Job processJob() {
        return jobBuilderFactory.get("processJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(orderStep1()).end().build();
    }

    @Bean
    public Step orderStep1() {
        return stepBuilderFactory.get("orderStep1").<String, String> chunk(1)
                .reader(new Reader())
                .processor(new Processor())
                .writer(new Writer())
                .build();
    }

    @Bean
    public JobExecutionListener listener() {
        return new JobCompleteListener();
    }

}
