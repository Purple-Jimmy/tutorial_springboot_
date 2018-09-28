package com.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * Created by Jimmy. 2018/2/9  15:06
 */
public class TopicSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(TopicSender.class);

    private static final String EXCHANGE_NAME = "topic_logs";
    private static final String[] ROUTER_KEY_ARR = {"dao.debug", "dao.info", "dao.error",
            "service.debug", "service.info", "service.error",
            "controller.debug", "controller.info", "controller.error"};

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("36.111.193.248");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 声明交换区:1.名字 2.类型 3.持久化
        channel.exchangeDeclare(EXCHANGE_NAME, "topic",false);
        // 发送消息
        for (String routerKey : ROUTER_KEY_ARR) {
            String message = "log : [" +routerKey+ "]" + UUID.randomUUID().toString();
            // 发布消息至交换器
            channel.basicPublish(EXCHANGE_NAME, routerKey, null, message.getBytes());
            LOGGER.info(" [x] Sent '" + message + "'");
        }
        // 关闭频道和连接
        channel.close();
        connection.close();
    }
}
