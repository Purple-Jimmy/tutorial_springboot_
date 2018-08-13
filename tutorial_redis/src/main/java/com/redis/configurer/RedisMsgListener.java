package com.redis.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**redis消息监听器容器
 * @Author: jimmy
 * @Date: 2018/8/13
 */
@Configuration
public class RedisMsgListener {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                            MessageListenerAdapter listenerAdapter){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了一个叫test 的通道
        container.addMessageListener(listenerAdapter, new PatternTopic("test"));
        //这个container 可以添加多个 messageListener
        return container;
    }

    /**
     * 消息监听器适配器,绑定消息处理器,利用反射技术调用消息处理器的业务方法
     * MessageListenerAdapter通过反射调用receiveMessage方法处理消息
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(MessageReceiver receiver) {
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器,利用反射的方法调用receiveMessage
        //也有好几个重载方法,这边默认调用处理器的方法 叫handleMessage 可以自己到源码里面看
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}
