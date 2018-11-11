package com.batch.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jimmy
 * @date 2018/11/1122:47
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "success";
    }
}
