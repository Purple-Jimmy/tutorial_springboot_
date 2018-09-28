package com.rabbitmq.direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * Created by Jimmy. 2018/2/9  11:48
 */
public class DirectSender {
    private static final Logger LOGGER = LoggerFactory.getLogger(DirectSender.class);

    private static final String EXCHANGE_NAME = "direct_logs";
    private static final String[] LOG_LEVEL_ARR = {"debug", "info", "error"};

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.50.116");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 声明交换区:1.名字 2.类型 3.持久化
        channel.exchangeDeclare(EXCHANGE_NAME, "direct",false);
        // 发送消息
        for(int i = 0; i < 10; i++)  {
            int rand = new Random().nextInt(3);
            String severity  = LOG_LEVEL_ARR[rand];
            String message = "log : [" +severity+ "]" + UUID.randomUUID().toString();
            // 发布消息至交换区
            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
            LOGGER.info(" [x] Sent '" + message + "'");
        }
        // 关闭频道和连接
        channel.close();
        connection.close();
    }
}
