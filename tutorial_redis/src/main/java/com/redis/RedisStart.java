package com.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author: jimmy
 * @Date: 2018/8/13
 */
@SpringBootApplication
@EnableCaching
public class RedisStart {

    public static void main(String[] args) {
        SpringApplication.run(RedisStart.class,args);
    }
}
