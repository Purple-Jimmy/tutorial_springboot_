package com.rabbitmq.configurer;

import com.rabbitmq.util.RabbitExchangeType;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Jimmy. 2018/2/6  11:23
 */
@Configuration
public class RabbitMQConfigurer {
    public static final String DIRECT_QUEUE = "direct_queue";
    public static final String FANOUT_QUEUE_1 = "fanout_queue_1";
    public static final String FANOUT_QUEUE_2 = "fanout_queue_2";
    public static final String TOPIC_LOG_INFO_QUEUE  = "log.info";
    public static final String TOPIC_LOG_QUEUE  = "log.#";


    /*@Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("36.111.193.248",5672);

        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }*/

    //rabbitAdmin 用于管理 exchanges, queues and bindings等
   /* @Bean
    RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }*/

//---------------direct------------------------------------------------------------------------------------------
    /**
     * 声明Direct交换区
     * 参数1：String name
     * 参数2：boolean durable
     * 参数3：boolean autoDelete  当所有消费客户端连接断开后,是否自动删除队列
     * 参数arguments
     * @return
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(RabbitExchangeType.DIRECT.name(),true,false,null);
    }

    /**
     * 声明名字为direct_queue的queue
     * 参数1:String name
     * 参数2:boolean durable
     * 参数3:boolean exclusive   仅创建者可以使用的私有队列,断开后自动删除
     * 参数4:boolean autoDelete  当所有消费客户端连接断开后,是否自动删除队列
     * 参数5:Map arguments
     * @return
     */
    @Bean
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE,true,false,false,null);
    }

    /**
     * directQueue绑定directExchange
     * @param directQueue
     * @param directExchange
     * @return
     */
    @Bean
    public Binding bindingDirect(Queue directQueue,DirectExchange directExchange) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("key.direct");
    }

//-------------fanout------------------------------------------------------------------------------------
    /**
     * 声明Fanout交换区
     * 参数1：String name
     * 参数2：boolean durable
     * 参数3：boolean autoDelete
     * 参数4：Map arguments
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitExchangeType.FANOUT.name(),true,false,null);
    }

    /**
     * 声明名字为fanout_queue的queue
     * 参数1:String name
     * 参数2:boolean durable
     * 参数3:boolean exclusive
     * 参数4:boolean autoDelete
     * 参数5:Map arguments
     * @return
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE_1,true,false,false,null);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE_2,true,false,false,null);
    }

    /**
     * fanoutQueue绑定fanoutExchange
     * @return
     */
    @Bean
    public Binding bindingFanout1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingFanout2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }


//---------topic--------------------------------------------------------------------------------------------
    /**
     * 声明Topic交换区
     * 参数1：String name
     * 参数2：boolean durable
     * 参数3：boolean autoDelete
     * 参数4：Map arguments
     * @return
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(RabbitExchangeType.TOPIC.name(),true,false,null);
    }

    /**
     * 声明名字为topic_queue的queue 只接收log.info
     * 参数1:String name
     * 参数2:boolean durable
     * 参数3:boolean exclusive
     * 参数4:boolean autoDelete
     * 参数5:Map arguments
     * @return
     */
    @Bean
    public Queue topicQueueInfo() {
        return new Queue(TOPIC_LOG_INFO_QUEUE,true,false,false,null);
    }

    /**
     * topicQueue绑定topicExchange 绑定log.info
     * @return
     */
    @Bean
    public Binding bindingTopicInfo() {
        return BindingBuilder.bind(topicQueueInfo()).to(topicExchange()).with("log.info");
    }

    /**
     * 声明名字为topic_queue的queue 接收log.info或者log.error
     * 参数1:String name
     * 参数2:boolean durable
     * 参数3:boolean exclusive
     * 参数4:boolean autoDelete
     * 参数5:Map arguments
     * @return
     */
    @Bean
    public Queue topicQueueLogAll() {
        return new Queue(TOPIC_LOG_QUEUE,true,false,false,null);
    }

    /**
     * topicQueue绑定topicExchange 绑定log
     * @return
     */
    @Bean
    public Binding bindingTopicAll() {
        return BindingBuilder.bind(topicQueueLogAll()).to(topicExchange()).with("log.#");
    }
}
