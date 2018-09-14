package com.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jimmy
 * @Date: 2018/9/14
 */
@RestController
public class ProducerController {
    @Autowired
    KafkaTemplate kafkaTemplate;

    @RequestMapping("/producer")
    public String producer(){
        System.out.println(kafkaTemplate);
        kafkaTemplate.send("viper-search-ns","hello");
        return "success";
    }
}
