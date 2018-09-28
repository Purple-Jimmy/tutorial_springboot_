package com.rabbitmq.workqueue;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Jimmy. 2018/2/7  16:03
 */
public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        // 设置 RabbitMQ 的主机名
        factory.setHost("36.111.193.248");
        // 创建一个连接
        Connection connection = factory.newConnection();
        // 创建一个通道
        Channel channel = connection.createChannel();
        // 声明一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 公平转发,将发送到下一个还不忙的工作线程
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        // 创建队列消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

              //  System.out.println("consumerTag="+consumerTag);
             //   System.out.println("deliveryTag="+envelope.getDeliveryTag()+",consumerTag="+consumerTag+",Exchange名字'"+envelope.getExchange() + "'");
                try {
                    doWork(message);
                    //处理完成之后手动应答
                    channel.basicAck(envelope.getDeliveryTag(), false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        boolean autoAck = false;//关闭自动应答
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }

    private static void doWork(String message) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        LOGGER.info(message+"处理完成--消费者A");
    }
}