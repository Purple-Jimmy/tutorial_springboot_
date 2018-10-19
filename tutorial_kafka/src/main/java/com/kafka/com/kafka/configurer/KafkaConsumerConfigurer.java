package com.kafka.com.kafka.configurer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: jimmy
 * @Date: 2018/10/19
 */
@Configuration
public class KafkaConsumerConfigurer {

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(10);
     //   factory.getContainerProperties().setPollTimeout(1500);
        return factory;
    }
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> propsMap = new HashMap<>(20);
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.50.244:9092");
        propsMap.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "11000");
        //指定消息被消费之后自动提交偏移量(即消息的编号,表示消费到了哪个位置,消费者每消费完一条消息就会向kafka服务器汇报自己消费到的那个消息的编号,
        //以便于下次继续消费
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
     //   propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000");
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "10000");
     //   propsMap.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, "9000");
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        //消费者组
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, "jimmy");
        //从最近的地方开始消费
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        // 从partition中获取消息最大大小
        propsMap.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "102400");
        return propsMap;
    }
}
