package com.elasticsearch.configurer;

import org.elasticsearch.client.transport.TransportClient;

/**
 * @Author: jimmy
 * @Date: 2018/8/14
 */
public class EsTransportClient {

    private static TransportClient client;

    /**
     * 获取客户端
     *  private String url;
     *  private String serverIp;
     *  private int serverPort;
     * @return jestclient
     */
   /* public static synchronized TransportClient getClient(ElasticSearchCfg searchCfg) {
        if (client == null) {
            try {
                client = new PreBuiltTransportClient(Settings.EMPTY)
                        .addTransportAddress(new TransportAddress(InetAddress.getByName(searchCfg.getServerIp()), searchCfg.getServerPort()));

            } catch (Exception e) {
            }
        }
        return client;
    }*/
}
