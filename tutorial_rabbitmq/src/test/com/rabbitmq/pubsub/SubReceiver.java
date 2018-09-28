package com.rabbitmq.pubsub;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Jimmy. 2018/2/9  10:57
 */
public class SubReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubReceiver.class);
    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("36.111.193.248");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 声明交换区:1.名字 2.类型 3.持久化
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout",false);
        // 声明临时队列
        String queueName = channel.queueDeclare().getQueue();
        // 绑定交换器和队列:不要设置routingKey
        // 1.队列名 2.交换区名 3.routingKey
        channel.queueBind(queueName, EXCHANGE_NAME,"");
        // 创建队列消费者
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                LOGGER.info(" queue {} Received '" + message + "'",queueName);
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
