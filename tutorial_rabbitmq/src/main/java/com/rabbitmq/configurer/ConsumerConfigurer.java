package com.rabbitmq.configurer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.domain.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * RabbitHandler用于有多个方法时但是参数类型不能一样,否则会报错
 * Created by Jimmy. 2018/2/13  13:56
 */
@Component
public class ConsumerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerConfigurer.class);

//---------direct-----------------------------------------------------------------------------------------------

   /* @RabbitListener(queues = "direct_queue")
    @RabbitHandler
    public void processSend_direct(byte[] str,Channel channel,Message message) throws IOException {
        String content = new String(str, "UTF-8");
        LOGGER.info("接收到direct_queue的消息 {}", content);
        LOGGER.info("接收到direct_queue的tag {}",message.getMessageProperties().getDeliveryTag());
        // 确认消息已经消费成功,手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }*/


    @RabbitListener(queues = "direct_queue")
    @RabbitHandler
    public void processConvertAndSend_direct(@Payload Animal animal, Channel channel, Message message) throws InterruptedException, IOException {
        logger.info("接收到direct_queue的消息,开始处理,消息Id {},内容 {}",message.getMessageProperties().getDeliveryTag(), animal);
        TimeUnit.SECONDS.sleep(10);
        logger.info("消息{} 处理完成,发送ACK",animal);
        // 确认消息已经消费成功,手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

   /* @RabbitListener(queues = "direct_queue")
    @RabbitHandler
    public String convertSendAndReceive_direct(@Payload Animal animal, Channel channel, Message message) throws InterruptedException, IOException {
        LOGGER.info("接收到direct_queue的消息,开始处理,消息Id {},内容 {}",message.getMessageProperties().getDeliveryTag(), animal);
        TimeUnit.SECONDS.sleep(10);
        LOGGER.info("消息{} 处理完成,发送ACK",animal);
        // 确认消息已经消费成功,手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        return "direct_queue 处理成功"+animal.toString();
    }*/


//-------fanout----------------------------------------------------------------------------------------------------
   /* @RabbitListener(queues = "fanout_queue_1")
    @RabbitHandler
    public void processSend_fanout_1(byte[] str, Channel channel,Message message) throws IOException {
        String content = new String(str, "UTF-8");
        LOGGER.info("fanout_queue_1消息 {}", content);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = "fanout_queue_2")
    @RabbitHandler
    public void processSend_fanout_2(byte[] str,Channel channel,Message message) throws IOException {
        String content = new String(str, "UTF-8");
        LOGGER.info("fanout_queue_2消息 {}", content);
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }*/


    @RabbitListener(queues = RabbitMQConfigurer.FANOUT_QUEUE_1)
    @RabbitHandler
    public void processConvertAndSend_fanout_1(@Payload Animal animal, Channel channel, Message message) throws InterruptedException, IOException {
        logger.info("接收到fanout_queue_1的消息,开始处理,消息Id {},内容 {}",message.getMessageProperties().getDeliveryTag(), animal);
        TimeUnit.SECONDS.sleep(10);
        logger.info("消息{} 处理完成,发送ACK",animal);
        // 确认消息已经消费成功,手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

   // @RabbitListener(queues = RabbitMQConfigurer.FANOUT_QUEUE_2)
   // @RabbitHandler
    public void processConvertAndSend_fanout_2(@Payload Animal animal, Channel channel, Message message) throws InterruptedException, IOException {
        logger.info("接收到fanout_queue_2的消息,开始处理,消息Id {},内容 {}",message.getMessageProperties().getDeliveryTag(), animal);
        TimeUnit.SECONDS.sleep(10);
        logger.info("消息{} 处理完成,发送ACK",animal);
        // 确认消息已经消费成功,手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


   /* @RabbitListener(queues = "fanout_queue_1")
    @RabbitHandler
    public String convertSendAndReceive_fanout_1(@Payload Animal animal, Channel channel, Message message) throws InterruptedException, IOException {
        logger.info("接收到fanout_queue_1的消息,开始处理,消息Id {},内容 {}",message.getMessageProperties().getDeliveryTag(), animal);
        TimeUnit.SECONDS.sleep(10);
        logger.info("消息{} 处理完成,发送ACK",animal);
        // 确认消息已经消费成功,手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        return "fanout_queue_1 处理成功"+animal.toString();
    }

    @RabbitListener(queues = "fanout_queue_2")
    @RabbitHandler
    public String convertSendAndReceive_fanout_2(@Payload Animal animal, Channel channel, Message message) throws InterruptedException, IOException {
        logger.info("接收到fanout_queue_2的消息,开始处理,消息Id {},内容 {}",message.getMessageProperties().getDeliveryTag(), animal);
        TimeUnit.SECONDS.sleep(10);
        logger.info("消息{} 处理完成,发送ACK",animal);
        // 确认消息已经消费成功,手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        return "fanout_queue_2 处理成功"+animal.toString();
    }*/

//-------topic----------------------------------------------------------------------------------------------------------
    /*@RabbitListener(queues = RabbitMQConfigurer.TOPIC_LOG_INFO_QUEUE)
    @RabbitHandler
    public void processSend_topic_log_info(byte[] str,Channel channel,Message message) throws IOException {
        String content = new String(str, "UTF-8");
        logger.info("接收到log.info的消息 {}", content);
        logger.info("接收到log.info的tag {}",message.getMessageProperties().getDeliveryTag());
        // 确认消息已经消费成功,手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = RabbitMQConfigurer.TOPIC_LOG_QUEUE)
    @RabbitHandler
    public void processSend_topic_log_all(byte[] str,Channel channel,Message message) throws IOException {
        String content = new String(str, "UTF-8");
        logger.info("接收到log.#的消息 {}", content);
        logger.info("接收到log.#的tag {}",message.getMessageProperties().getDeliveryTag());
        // 确认消息已经消费成功,手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }*/

    @RabbitListener(queues = RabbitMQConfigurer.TOPIC_LOG_INFO_QUEUE)
    @RabbitHandler
    public void processConvertAndSend_topic_log_info(@Payload Animal animal, Channel channel, Message message) throws InterruptedException, IOException {
        logger.info("接收到log.info的消息,开始处理,消息Id {},内容 {}",message.getMessageProperties().getDeliveryTag(), animal);
        TimeUnit.SECONDS.sleep(10);
        logger.info("消息{} 处理完成,发送ACK",animal);
        // 确认消息已经消费成功,手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    @RabbitListener(queues = RabbitMQConfigurer.TOPIC_LOG_QUEUE)
    @RabbitHandler
    public void processConvertAndSend_topic_log_all(@Payload Animal animal, Channel channel, Message message) throws InterruptedException, IOException {
        logger.info("接收到log.#的消息,开始处理,消息Id {},内容 {}",message.getMessageProperties().getDeliveryTag(), animal);
        TimeUnit.SECONDS.sleep(10);
        logger.info("消息{} 处理完成,发送ACK",animal);
        // 确认消息已经消费成功,手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }


    //-------------优先级消息队列-------------------------------------------------------------
    @RabbitListener(queues = RabbitMQConfigurer.PRIORITY_QUEUE)
    @RabbitHandler
    public void processConvertAndSend_priority(@Payload Animal animal, Channel channel, Message message) throws InterruptedException, IOException {
        logger.info("接收到key.priority.#的消息,开始处理,消息Id {},优先级 {},内容 {}",message.getMessageProperties().getDeliveryTag(),message.getMessageProperties().getPriority(), animal);
        TimeUnit.SECONDS.sleep(10);
        logger.info("消息{} 处理完成,发送ACK",animal);
        // 确认消息已经消费成功,手动ACK
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
