package com.elasticsearch.configurer;

/**
 * @Author: jimmy
 * @Date: 2018/9/18
 * JestClient默认是单例的,在多线程并发访问时注意加锁同步或者使用异步执行jestClient.executeAsync(),
 * 或者自定义JestClientFactory设置HttpClientConfig的多线程属性为true
 */
public class JestConfigurer {
}
