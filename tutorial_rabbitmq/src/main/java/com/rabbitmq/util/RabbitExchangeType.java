package com.rabbitmq.util;

/**
 * Created by Jimmy. 2018/2/9  17:26
 */
public enum RabbitExchangeType {

    DIRECT("direct"),FANOUT("fanout"),TOPIC("topic"),PRIORITY("priority");

    private String name;

    //构造函数
    RabbitExchangeType(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
