package com.elasticsearch.controller;

import io.searchbox.client.JestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jimmy
 * @Date: 2018/9/18
 */
@RestController
public class TestController {
    @Autowired
    JestClient jestClient;

    @RequestMapping("/test")
    public void test(){
       // System.out.println(jestClient.toString());
    }
}
