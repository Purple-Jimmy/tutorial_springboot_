package com.elasticsearch.configurer;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.sniff.Sniffer;
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
    Sniffer sniffer;

    @PostConstruct
    public void init(){
        String[] hostArray = urls.split(",");
        Arrays.stream(hostArray).forEach(s->{
            String[] strArray = s.split(":");
            hostList.add(new HttpHost(strArray[0],Integer.parseInt(strArray[1]),"http"));
        });
    }


    /**
     *
     * @return
     */
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        //credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("", ""));

        RestClientBuilder builder = RestClient.builder(hostList.toArray(new HttpHost[0]));

        //设置每个请求需要发送的默认headers,这样就不用在每个请求中指定它们
        Header[] defaultHeaders = new Header[]{new BasicHeader("header", "value")};
        builder.setDefaultHeaders(defaultHeaders);

        //设置一个监听程序,每次节点发生故障时都会收到通知,这样就可以采取相应的措施。
        builder.setFailureListener(new RestClient.FailureListener() {
            public void onFailure(HttpHost host) {
                //Used internally when sniffing on failure is enabled.(这句话没搞懂啥意思)
            }
        });

        // 异步httpClient连接延时配置
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
        // 异步httpClient连接数配置
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                httpClientBuilder.setMaxConnTotal(maxConnectNum);
                httpClientBuilder.setMaxConnPerRoute(maxConnectPerRoute);
                //The Apache Http Async Client默认启动一个dispatcher线程和供连接管理器使用的多个worker线程,
                //与本地检测到的处理器数量一样多(取决于Runtime.getRuntime().availableProcessors()的返回值)
                httpClientBuilder.setDefaultIOReactorConfig(IOReactorConfig.custom().setIoThreadCount(1).build());
                //配置认证
                //禁用抢占式身份验证:意味着每个请求都将被发送,不用去看授权请求头,在收到HTTP 401响应后,会再次发送相同的请求,这次会带上基本的身份认证头
                //httpClientBuilder.disableAuthCaching();
                //httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                return httpClientBuilder;
            }
        });
        //最大重试超时时间(默认为30秒）
        builder.setMaxRetryTimeoutMillis(6000);
        //监听同网段服务
        //SnifferBuilder snifferBuilder = Sniffer.builder(builder.build()).setSniffIntervalMillis(6000);
        //嗅探器:RestClient实例创建后,就可以将一个Sniffer关联到它.Sniffer使用RestClient定期(默认每5分钟)从集群中获取当前所有节点的列表,并通过调用RestClient的setHosts方法来更新
        //sniffer = snifferBuilder.build();
        //SniffOnFailureListener sniffOnFailureListener = new SniffOnFailureListener();
        //sniffOnFailureListener.setSniffer(sniffer);
        //builder.setFailureListener(sniffOnFailureListener);
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder);
        restClient = restHighLevelClient;
        return restHighLevelClient;
    }


    @PreDestroy
    public void close(){
        System.out.println("关闭资源");
        try {
            sniffer.close();
            restClient.close();
        } catch (IOException e) {
            log.info("restHighLevelClient 关闭失败");
        }
    }




   /* setSSLContext，setSSLSessionStrategy和setConnectionManager，重要性依次增加。 以下是一个例子：
    KeyStore truststore = KeyStore.getInstance("jks");
try (
    InputStream is = Files.newInputStream(keyStorePath)) {
        truststore.load(is, keyStorePass.toCharArray());
    }
    SSLContextBuilder sslBuilder = SSLContexts.custom().loadTrustMaterial(truststore, null);
    final SSLContext sslContext = sslBuilder.build();
    RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "https"))
            .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                @Override
                public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                    return httpClientBuilder.setSSLContext(sslContext);
                }
            });*/


   /* Elasticsearch Nodes Info api在连接到节点时不会返回使用的协议，而只会返回它们的host：port键对，因此默认情况下使用http。 如果想使用https，则必须手动创建ElasticsearchHostsSniffer实例

    RestClient restClient = RestClient.builder(
            new HttpHost("localhost", 9200, "http"))
            .build();
    HostsSniffer hostsSniffer = new ElasticsearchHostsSniffer(
            restClient,
            ElasticsearchHostsSniffer.DEFAULT_SNIFF_REQUEST_TIMEOUT,
            ElasticsearchHostsSniffer.Scheme.HTTPS);
    Sniffer sniffer = Sniffer.builder(restClient)
            .setHostsSniffer(hostsSniffer).build();*/
}
