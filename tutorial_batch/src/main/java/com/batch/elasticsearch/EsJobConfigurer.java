package com.batch.elasticsearch;

import com.batch.domain.Program;
import com.batch.listener.JobCompleteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 * @author jimmy
 * @date 2019-02-2622:12
 */
@Configuration
public class EsJobConfigurer {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;
    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    EsPagingItemReader esPagingItemReader;

    /**
     * JobParameters 相同的任务只能成功运行一次
     * 如果希望该任务是周期执行的,那么必须保证周期内参数是唯一
     * 因此配置Job时需要使用incrementer方法为每次执行创建一个递增的ID保证唯一性,否则任务仅会执行一次
     * @return
     * processJob 定义job的名字
     */
    @Bean
    public Job esJob(@Qualifier("esStep") Step esStep) {
        return jobBuilderFactory.get("esJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(esStep).end().build();
    }

    @Bean
    public Step esStep(@Qualifier("esWriter")FlatFileItemWriter<Program> esWriter) {
        return stepBuilderFactory.get("esStep").<Program, Program> chunk(1)
                .reader(esPagingItemReader)
                .processor((ItemProcessor<Program, Program>) item ->
                {
                    return item;
                })
                .writer(esWriter)
                .build();
    }

    @Bean
    public FlatFileItemWriter<Program> esWriter() {
        FlatFileItemWriter<Program> writer = null;
        try {
            writer = new FlatFileItemWriter<Program>();
            writer.setAppendAllowed(true);
            writer.setEncoding("UTF-8");
            writer.setResource(new FileSystemResource("file/hello.txt"));
            writer.setLineAggregator(new DelimitedLineAggregator<Program>() {{
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor<Program>() {{
                    setNames(new String[]{"id","name"});
                }});
            }});
        } catch (Exception e) {

        }
        return writer;
    }

    public JobExecutionListener listener() {
        return new JobCompleteListener();
    }
}
