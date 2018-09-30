package com.tutorial.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: jimmy
 * @Date: 2018/9/30
 * 限流
 */
@RestController
public class RateLimitController {
    private static final Long TOTAL_COUNT = 3L;
    AtomicLong atomic = new AtomicLong();
    /**
     * 限流某个接口的总并发/请求数
     * 利用Java中的AtomicLong
     * @return
     */
    @RequestMapping("rateLimitTotalCount")
    public String rateLimitTotalCount(){
        String str = "处理";
        try{
            if(atomic.incrementAndGet() > TOTAL_COUNT) {
                //拒绝请求
                str = "不处理";
            } else {
                //处理请求
                System.out.println("处理");
            }
        }finally {
           // atomic.decrementAndGet();
        }
        return str;
    }
}
