package com.themeleaf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jimmy
 * @Date: 2018/7/27
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        return "c";
    }
}
