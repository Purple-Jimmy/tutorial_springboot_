package com.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

/**
 * @Author: jimmy
 * @Date: 2018/9/14
 */
@SpringBootApplication(exclude = {KafkaAutoConfiguration.class})
public class KafkaStart {

    public static void main(String[] args) {
        SpringApplication.run(KafkaStart.class,args);
    }
}
