package com.cassandra.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jimmy
 * @date 2018-12-1822:05
 */
@RestController
public class TestController {

    @RequestMapping("test")
    public String test(){
        return "success";
    }
}
