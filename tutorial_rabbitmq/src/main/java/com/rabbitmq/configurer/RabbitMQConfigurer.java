package com.rabbitmq.configurer;

import com.rabbitmq.util.RabbitExchangeType;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
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
    public static final String PRIORITY_FIRST_QUEUE = "priority_first_queue";
    public static final String PRIORITY_SECOND_QUEUE = "priority_second_queue";

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
     * 参数2:boolean durable     是否持久化,rabbitmq重启的时候不需要创建新的队列
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



 //---------优先级队列--------------------------------------------------------------------------------------------

    @Bean
    DirectExchange priorityFirstExchange() {
        return new DirectExchange(RabbitExchangeType.DIRECT.name(),true,false,null);
    }

    @Bean
    public Queue priorityFirstQueue() {
        return new Queue(DIRECT_QUEUE,true,false,false,null);
    }

    @Bean
    public Binding bindingFirstPriority(Queue priorityFirstQueue,DirectExchange priorityFirstExchange) {
        return BindingBuilder.bind(priorityFirstQueue).to(priorityFirstExchange).with("key.first");
    }

    @Bean
    DirectExchange prioritySecondExchange() {
        return new DirectExchange(RabbitExchangeType.DIRECT.name(),true,false,null);
    }

    @Bean
    public Queue prioritySecondQueue() {
        return new Queue(DIRECT_QUEUE,true,false,false,null);
    }

    @Bean
    public Binding bindingSecondPriority(Queue prioritySecondQueue,DirectExchange prioritySecondExchange) {
        return BindingBuilder.bind(prioritySecondQueue).to(prioritySecondExchange).with("key.second");
    }


 //------------------------------------------------------------------------------------------------------
    /**
     * 定义消息转换实例  转化成 JSON 传输  传输实体就可以不用实现序列化
     * */
    @Bean
    public MessageConverter integrationEventMessageConverter() {
        return  new Jackson2JsonMessageConverter();
    }

}
