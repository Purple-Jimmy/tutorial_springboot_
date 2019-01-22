package com.tutorial.controller;

import com.tutorial.configurer.PropertiesLoadConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jimmy
 * @Date: 2019/1/22
 */
@RestController
public class PropertiesController {

    @RequestMapping("/propTest")
    public String propertiesTest(){
        String val = PropertiesLoadConfigurer.getProperty("电影");
        System.out.println(val);
        return val;
    }
}
