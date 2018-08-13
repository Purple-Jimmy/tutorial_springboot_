package com.redis.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 发布/订阅
 * @Author: jimmy
 * @Date: 2018/8/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class PubSubDemo {
    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void publish(){
        System.out.println("-=================================");
        redisTemplate.convertAndSend("test-channel","this is test");
    }

}
