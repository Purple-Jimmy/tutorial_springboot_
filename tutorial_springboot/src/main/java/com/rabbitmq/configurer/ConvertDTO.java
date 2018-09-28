package com.rabbitmq.configurer;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: jimmy
 * @Date: 2018/9/25
 * Spring应用程序的内部实体和被发送到客户端的外部DTO(数据传输对象)之间的转换
 */
@Configuration
public class ConvertDTO {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    @Override
    public String toString() {
        return "ConvertDTO{}";
    }
}
