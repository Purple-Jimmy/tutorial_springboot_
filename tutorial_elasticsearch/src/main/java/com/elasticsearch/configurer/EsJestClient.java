package com.elasticsearch.configurer;

import com.google.gson.GsonBuilder;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

/**
 * @Author: jimmy
 * @Date: 2018/9/18
 */
public class EsJestClient {

    private static JestClient client;

    /**
     * 获取客户端
     * @param esHostUrl
     * @return
     */
    public static synchronized JestClient getClient(String esHostUrl) {
        if (client == null) {
            build(esHostUrl);
        }
        return client;
    }

    /**
     * 关闭客户端
     */
    public static void close(JestClient client) {
        if (client != null) {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 建立连接
     */
    private static void build(String esHostUrl) {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(
                new HttpClientConfig
                        .Builder(esHostUrl)
                        .multiThreaded(true)
                        //一个route 默认不超过2个连接
                        //路由是指连接到某个远程主机的个数.总连接数=route个数 * defaultMaxTotalConnectionPerRoute
                        .defaultMaxTotalConnectionPerRoute(2)
                        //所有route连接总数
                        .maxTotalConnection(5)
                        .connTimeout(10000)
                        .readTimeout(10000)
                        .gson(new GsonBuilder()
                                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                                .create())
                        .build()
        );
        client = factory.getObject();
    }

}
