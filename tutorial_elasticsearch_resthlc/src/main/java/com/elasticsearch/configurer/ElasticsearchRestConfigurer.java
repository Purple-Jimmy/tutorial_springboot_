package com.elasticsearch.configurer;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.sniff.SniffOnFailureListener;
import org.elasticsearch.client.sniff.Sniffer;
import org.elasticsearch.client.sniff.SnifferBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
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
    List<HttpHost> hostList = new ArrayList<>();
    RestHighLevelClient restClient;


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
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        //credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("", ""));

        RestClientBuilder builder = RestClient.builder(hostList.toArray(new HttpHost[0]));

        // 异步httpclient连接延时配置
        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                //连接超时(默认为1秒）
                requestConfigBuilder.setConnectTimeout(connectTimeOut);
                //套接字超时(默认为30秒）
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
                //The Apache Http Async Client默认启动一个dispatcher线程和供连接管理器使用的多个worker线程,
                //与本地检测到的处理器数量一样多(取决于Runtime.getRuntime().availableProcessors()的返回值)
                httpClientBuilder.setDefaultIOReactorConfig(IOReactorConfig.custom().setIoThreadCount(1).build());
                return httpClientBuilder;
            }
        });
        //最大重试超时时间(默认为30秒）
        builder.setMaxRetryTimeoutMillis(6000);
        //监听同网段服务
        SnifferBuilder snifferBuilder = Sniffer.builder(builder.build()).setSniffIntervalMillis(1000);
        //嗅探器
        Sniffer sniffer = snifferBuilder.build();
        SniffOnFailureListener sniffOnFailureListener = new SniffOnFailureListener();
        sniffOnFailureListener.setSniffer(sniffer);
        builder.setFailureListener(sniffOnFailureListener);
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder);
        restClient = restHighLevelClient;
        return restHighLevelClient;
    }


    @PreDestroy
    public void close(){
        System.out.println("关闭资源");
        try {
            restClient.close();
        } catch (IOException e) {
            log.info("restHighLevelClient 关闭失败");
        }
    }




}
