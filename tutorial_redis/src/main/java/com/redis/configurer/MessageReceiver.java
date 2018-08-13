package com.redis.configurer;

import org.springframework.stereotype.Component;

/**
 * 消息处理器
 * @Author: jimmy
 * @Date: 2018/8/13
 */
@Component
public class MessageReceiver {

    /**
     * 接收消息的方法
     * @param message
     */
    public void receiveMessage(String message){
        System.out.println("收到一条消息："+message);
    }
}
