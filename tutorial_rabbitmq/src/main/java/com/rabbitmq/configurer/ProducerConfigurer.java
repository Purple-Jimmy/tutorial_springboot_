package com.rabbitmq.configurer;

import com.rabbitmq.util.RabbitExchangeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * @author jimmy
 * @date 2018/11/1323:08
 * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调
 * ReturnCallback接口用于实现消息发送到RabbitMQ交换器但无相应队列与交换器绑定时的回调
 */
@Component
@Slf4j
public class ProducerConfigurer implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     *  被@PostConstruct修饰的方法会在服务器加载Servle的时候运行并且只会被服务器执行一次.
     *  PostConstruct在构造函数之后执行,init()方法之前执行.
     */
    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }


    /**
     * 当消息成功到达exchange的时候触发的ack回调
     * 实现消息发送到RabbitMQ交换器后接收ack回调,如果消息发送确认失败就进行重试.
     * 若使用confirm-callback或return-callback
     * 必须要配置publisherConfirms或publisherReturns为true
     * 每个rabbitTemplate只能有一个confirm-callback和return-callback
     * 使用return-callback时必须设置mandatory为true,或者在配置中设置mandatory-expression的值为true
     * 可针对每次请求的消息去确定’mandatory’的boolean值
     * 只能在提供’return -callback’时使用,与mandatory互斥
     * Confirms给客户端一种轻量级的方式，能够跟踪哪些消息被broker处理
     * 哪些可能因为broker宕掉或者网络失败的情况而重新发布
     * 确认并且保证消息被送达,提供了两种方式:发布确认和事务(两者不可同时使用)
     * 在channel为事务时,不可引入确认模式;同样channel为确认模式下,不可使用事务
     *
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("消息发送成功:{}",correlationData);
        } else {
            log.info("消息发送失败:{}",cause);
        }

    }

    /**
     * 当消息成功到达exchange,但是没有队列与之绑定的时候触发的ack回调
     * 用于实现消息发送到RabbitMQ交换器,但无相应队列与交换器绑定时的回调.
     * 基本上来说线程不可能出现这种情况,除非手动将已经存在的队列删掉,否则在测试阶段肯定能测试出来.
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("消息发送失败:{}",message.getMessageProperties().getCorrelationId());
    }

    /**
     * convertAndSend 异步,消息是否发送成功用ConfirmCallback和ReturnCallback回调函数类确认。
     * 发送MQ消息
     */
    public void sendMessage(String exchangeName, String routingKey, Object message) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message, new CorrelationData(UUID.randomUUID().toString()));
    }

    /**
     * sendMessageAndReturn 当发送消息过后,该方法会一直阻塞在哪里等待返回结果,直到请求超时,配置spring.rabbitmq.template.reply-timeout来配置超时时间。
     * 发送MQ消息并返回结果
     */
    public Object sendMessageAndReturn(String exchangeName, String routingKey, Object message) {
        return rabbitTemplate.convertSendAndReceive(exchangeName, routingKey, message, new CorrelationData(UUID.randomUUID().toString()));
    }


//--------------direct--------------------------------------------------------------------------------------------
    /**
     * 发送消息,不需要实现任何接口,供外部调用
     * @param str
     */
    public void send_direct(String str){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        Message message = new Message(str.getBytes(), new MessageProperties());
        rabbitTemplate.send(RabbitExchangeType.DIRECT.name(), "key.direct", message, correlationData);
    }

    /**
     * 转换并发送消息.将参数对象转换为org.springframework.amqp.core.Message后发送.
     * 这个是异步的.消息是否发送成功需要用到ConfirmCallback和ReturnCallback回调函数类确认.
     * @param object
     */
    public void convertAndSend_direct(Object object){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitExchangeType.DIRECT.name(), "key.direct", object, correlationData);
    }

    /**
     * 转换并发送消息,且等待消息者返回响应消息.
     * 这是一个RPC方法,当发送消息过后,该方法会一直阻塞在哪里等待返回结果,直到请求超时.
     * 可以通过配置spring.rabbitmq.template.reply-timeout来配置超时时间
     * @param object
     */
    public void convertSendAndReceive_direct(Object object){
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        String response = rabbitTemplate.convertSendAndReceive(RabbitExchangeType.DIRECT.name(), "key.direct", object, correlationData).toString();
        log.info("direct 消费者响应:{},消息处理完成",response);
    }

//--------------fanout--------------------------------------------------------------------------------------------

    public void send_fanout(String str) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        Message message = new Message(str.getBytes(), new MessageProperties());
        rabbitTemplate.send(RabbitExchangeType.FANOUT.name(),"", message, correlationData);
    }

    public void convertAndSend_fanout(Object object) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitExchangeType.FANOUT.name(), "", object, correlationData);
    }

    public void convertSendAndReceive_fanout(Object object) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        String response = rabbitTemplate.convertSendAndReceive(RabbitExchangeType.FANOUT.name(), "", object, correlationData).toString();
        log.info("fanout 消费者响应:{},消息处理完成", response);
    }

//--------------topic--------------------------------------------------------------------------------------------

    public void send_topic(String routingKey,String str) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        Message message = new Message(str.getBytes(), new MessageProperties());
        rabbitTemplate.send(RabbitExchangeType.TOPIC.name(),routingKey, message, correlationData);
    }

    public void convertAndSend_topic(String routingKey,Object object) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitExchangeType.TOPIC.name(), routingKey, object, correlationData);
    }

    public void convertSendAndReceive_topic(String routingKey,Object object) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitExchangeType.TOPIC.name(), routingKey, object, correlationData);
    }

    //--------------优先级消息队列--------------------------------------------------------------------------------------------


    public void convertAndSend_priority(String routingKey,Object object,Integer priority) {
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitExchangeType.PRIORITY.name(), routingKey, object, messagePostProcessor -> {
            messagePostProcessor.getMessageProperties().setPriority(priority);
            return messagePostProcessor;
        });
    }



}
