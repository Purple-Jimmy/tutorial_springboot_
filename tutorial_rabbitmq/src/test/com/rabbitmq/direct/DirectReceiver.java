package com.rabbitmq.direct;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Jimmy. 2018/2/9  11:48
 */
public class DirectReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(DirectReceiver.class);

    private static final String EXCHANGE_NAME = "direct_logs";
    private static final String[] LOG_LEVEL_ARR = {"debug", "info", "error"};

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.50.116");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 声明交换区:1.名字 2.类型 3.持久化
        channel.exchangeDeclare(EXCHANGE_NAME, "direct",false);
        // 设置日志级别
        int rand = new Random().nextInt(3);
        String severity  = LOG_LEVEL_ARR[rand];
        // 声明一个临时队列
        String queueName = channel.queueDeclare().getQueue();
        // 绑定交换器和队列并指定routingKey
        // queueBind(String queue, String exchange, String routingKey)
        channel.queueBind(queueName, EXCHANGE_NAME, severity);
        // 创建队列消费者
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(),false);
                LOGGER.info(" queue {} Received '" + message + "'",queueName);
            }
        };
        //
        channel.basicConsume(queueName, false, consumer);
    }
}
