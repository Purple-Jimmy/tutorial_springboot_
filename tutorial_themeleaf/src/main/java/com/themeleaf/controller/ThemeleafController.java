package com.themeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Author: jimmy
 * @Date: 2018/7/27
 */
@Controller
public class ThemeleafController {

    @RequestMapping("/hello")
    public String hello(Map<String,Object> map){
        map.put("name","jimmy");
        return "hello";
    }
}
