package com.elasticsearch.configurer;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @Author: jimmy
 * @Date: 2018/9/18
 * JestClient默认是单例的,在多线程并发访问时注意加锁同步或者使用异步执行jestClient.executeAsync(),
 * 或者自定义JestClientFactory设置HttpClientConfig的多线程属性为true
 */
//@Configuration
public class JestConfigurer {

    @Value("${spring.elasticsearch.jest.uris}")
    private String url;

    @Bean
    public JestClient jestClient(){
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(url)
                .multiThreaded(true)
                //Per default this implementation will create no more than 2 concurrent connections per given route
                .defaultMaxTotalConnectionPerRoute(10)
                // and no more 20 connections in total
                .maxTotalConnection(10)
                .build());
        JestClient jestClient = factory.getObject();
        System.out.println(jestClient);
        return jestClient;
    }
}
