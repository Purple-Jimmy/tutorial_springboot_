package com.elasticsearch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jimmy
 * @Date: 2019/3/21
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "success";
    }
}
