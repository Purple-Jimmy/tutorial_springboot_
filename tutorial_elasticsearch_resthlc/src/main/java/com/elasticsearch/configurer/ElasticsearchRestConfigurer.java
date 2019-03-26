package com.elasticsearch.configurer;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: jimmy
 * @Date: 2019/3/21
 */
@Slf4j
@Configuration
public class ElasticsearchRestConfigurer {
    @Value("${elasticsearch.urls}")
    String urls;

    @Value("${elasticsearch.username}")
    private String userName;

    @Value("${elasticsearch.password}")
    private String password;

    /**
     * 连接超时时间
     */
    private static int connectTimeOut = 1000;
    /**
     * 连接超时时间
     */
    private static int socketTimeOut = 30000;
    /**
     *  获取连接的超时时间
     */
    private static int connectionRequestTimeOut = 500;
    /**
     * 最大连接数
     */
    private static int maxConnectNum = 100;
    /**
     * 最大路由连接数
     */
    private static int maxConnectPerRoute = 100;



    private static final int ADDRESS_LENGTH = 2;
    private static final String HTTP_SCHEME = "http";

    List<HttpHost> hostList = new ArrayList<>();

    @PostConstruct
    public void init(){
        String[] hostArray = urls.split(",");
        Arrays.stream(hostArray).forEach(s->{
            String[] strArray = s.split(":");
            hostList.add(new HttpHost(strArray[0],Integer.parseInt(strArray[1]),"http"));
        });
    }



    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestClientBuilder builder = RestClient.builder(hostList.toArray(new HttpHost[0]));
        // 异步httpclient连接延时配置
        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                requestConfigBuilder.setConnectTimeout(connectTimeOut);
                requestConfigBuilder.setSocketTimeout(socketTimeOut);
                requestConfigBuilder.setConnectionRequestTimeout(connectionRequestTimeOut);
                return requestConfigBuilder;
            }
        });
        // 异步httpclient连接数配置
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                httpClientBuilder.setMaxConnTotal(maxConnectNum);
                httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                return httpClientBuilder;
            }
        });
        RestHighLevelClient client = new RestHighLevelClient(builder);
        return client;
    }







}
