package com.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author jimmy
 * @date 2018/11/1122:43
 * @EnableBatchProcessing是打开Batch,如果要实现多Job的情况,需要把EnableBatchProcessing注解的modular设置为true
 */
@SpringBootApplication
@EnableBatchProcessing
//@EnableBatchProcessing(modular = true)
public class BatchStart {

    public static void main(String[] args) {
        SpringApplication.run(BatchStart.class,args);
    }

}
