package com.tutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: Jimmy
 * @Date: 2018/7/24
 * @EnableTransactionManagement 开启事务
 */
@SpringBootApplication(exclude={MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
@EnableTransactionManagement
public class SpringbootStart {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootStart.class,args);
    }


}
