package com.batch.configurer;

import com.batch.domain.Message;
import com.batch.domain.PayMessage;
import com.batch.exception.MoneyNotEnoughException;
import com.batch.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

/**
 * @author jimmy
 * @date 2018/11/1815:52
 */
@Configuration
@Slf4j
public class JpaSampleConfigurer {
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private MessageRepository messageRepository;

    private int CHUNK_SIZE = 1;
    private int SKIP_LIMIT = 2;
    private int LT_NUM = 70;

    /**
     * 统计出price>10的id 并存入payMessage
     * @return
     */
    @Bean
    public Step step1(){
        return stepBuilderFactory.get("step1").<Message, PayMessage>chunk(CHUNK_SIZE)
                .reader(new JpaPagingItemReader<Message>() {{
                    setQueryString("select m from Message m");
                    setEntityManagerFactory(entityManagerFactory);}})
                .processor((ItemProcessor<Message, PayMessage>) item ->
                {
                    if (item.getPrice() < LT_NUM) {
                        return null;
                    } else {
                        PayMessage payMessage = new PayMessage();
                        payMessage.setObjectId(item.getObjectId());
                        payMessage.setContent(item.getContent());
                        //
                        //item.setPrice();
                        return payMessage;
                    }
                })
                .writer(new JpaItemWriter<PayMessage>() {{
                    setEntityManagerFactory(entityManagerFactory);
                }})
                .build();
    }

    /**
     * message中price减去30
     * @return
     */
    @Bean
    public Step step2(){
        return stepBuilderFactory.get("step2").<Message, PayMessage>chunk(CHUNK_SIZE)
                .faultTolerant()
                // 允许忽略的异常,否则会以Job Failed结束
                .skip(MoneyNotEnoughException.class)
                // 允许最大跳过100个余额不足数据
                .skipLimit(3)
                .reader(new JpaPagingItemReader<Message>() {{
                    setQueryString("select m from Message m");
                    setEntityManagerFactory(entityManagerFactory);}})
                .processor((ItemProcessor<Message,PayMessage>) item -> {
                    if (item.getPrice() < LT_NUM) {
                        System.out.println("余额不足"+item.getObjectId());
                        //余额不足
                        throw new MoneyNotEnoughException();
                    }else{
                        item.setIsPayed(true);
                        messageRepository.save(item);
                    }
                        return null;
                })
                .writer(new JpaItemWriter<PayMessage>() {{
                    //setEntityManagerFactory(entityManagerFactory);
                }})
                .build();
    }

    /**
     * 发通知
     * @return
     */
    @Bean
    public Step step3(){
        return stepBuilderFactory.get("step3")
                .tasklet((contribution, chunkContext) -> {
                    /*List<MonthBill> monthBills = monthBillRepository.findUnpaidMonthBill(
                            DateUtils.getBeginDayOfMonth(), DateUtils.getEndDayOfMonth());
                    for (MonthBill monthBill : monthBills) {
                        System.out.println(
                                String.format("Message sent to UserID %s ——> your water bill this month is ￥%s，please pay for it",
                                        monthBill.getUserId(), monthBill.getTotalFee()));
                    }*/
                    System.out.println("notice。。。。。。");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }


    @Bean
    public Job jpaJob(Step step1, Step step2, Step step3) {
        return jobBuilderFactory.get("jpaJob")
                .listener(new JobExecutionListener() {
                    private Long time;
                    @Override
                    public void beforeJob(JobExecution jobExecution) {
                        time = System.currentTimeMillis();
                    }

                    @Override
                    public void afterJob(JobExecution jobExecution) {
                        System.out.println(jobExecution.getStatus());
                        System.out.println(String.format("任务耗时：%sms", System.currentTimeMillis() - time));
                    }
                })
                .flow(step1)
                .next(step2)
                .next((jobExecution, stepExecution) -> {
                    if (stepExecution.getExitStatus().equals(ExitStatus.COMPLETED) &&
                            stepExecution.getSkipCount() > 0) {
                        return new FlowExecutionStatus("NOTICE USER");
                    } else {
                        return new FlowExecutionStatus(stepExecution.getExitStatus().toString());
                    }
                })
                .on("COMPLETED").end()
                .on("NOTICE USER").to(step3)
                .end()
                .build();
    }

}
