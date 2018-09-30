package com.tutorial.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jimmy
 * @Date: 2018/9/30
 */
@RestController
@Slf4j
public class LogController {

    @RequestMapping("log")
    public String log(){
        log.info("测试全局日志-=============================");
        return "success";
    }
}
