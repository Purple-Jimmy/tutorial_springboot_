package com.redis.configurer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: jimmy
 * @Date: 2018/8/13
 */
//@Configuration
public class RedisConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(RedisConfigurer.class);

    @Autowired
    private LettuceConnectionFactory lettuceConnectionFactory;

    /**
     * StringRedisTemplate继承自RedisTemplate,只能操作键值都是String类型的数据
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        logger.info("StringRedisTemplate 初始化...");
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //redisTemplate.setValueSerializer(new RedisObjectSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    /**
     * 对象转成json格式
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(){
        logger.info("redisTemplate 初始化...");
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        redisTemplate.setKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashKeySerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

}




//private String host;
//private String port;


   /* @Bean
    public LettuceConnectionFactory redisStringConnectionFactory(){
        LettuceConnectionFactory factory = new LettuceConnectionFactory();
        factory.setDatabase(10);
        factory.set
        return factory;
    }*/

