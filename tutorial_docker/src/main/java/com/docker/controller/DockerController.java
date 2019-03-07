package com.docker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jimmy
 * @Date: 2019/3/7
 */
@RestController
public class DockerController {

    @RequestMapping("docker")
    public void docker(){
        System.out.println("hello");
        System.out.printf("docker");
    }
}
