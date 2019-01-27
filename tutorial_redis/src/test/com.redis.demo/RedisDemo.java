package com.redis.demo;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: jimmy
 * @Date: 2018/9/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest()
public class RedisDemo {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //@Test
    public void prefixDemo(){
     //   stringRedisTemplate.opsForValue().set("test:1","1");
     //   stringRedisTemplate.opsForValue().set("test:2","5");

       // redisTemplate.opsForValue().set("h:1",21);
      //  redisTemplate.opsForValue().set("h:2",22);
        System.out.println(stringRedisTemplate.opsForValue().get("test:1"));
        System.out.println(stringRedisTemplate.keys("test:*"));
    }
}
