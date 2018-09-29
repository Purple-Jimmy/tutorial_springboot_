package com.tutorial.controller;

import com.tutorial.domain.User;
import com.utils.common.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Jimmy
 * @Date: 2018/7/24
 */
@RestController
@RequestMapping("/rest")
@Slf4j
public class RestfulTestController {
    /**
     * @PathVariable
     * 请求URL:http://localhost:8080/rest/1
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/user/{id}/{name}")
    public String queryUser1(@PathVariable("id")Long id,@PathVariable("name")String name){
        log.info("rest query user by id {},name {}",id,name);
        return Constant.SUCCESS;
    }

    /**
     * @RequestParam
     * 请求URL:http://localhost:8080/rest/user?name=jimmy
     * @param name
     * @return
     */
    @GetMapping("/user")
    public String queryUser2(@RequestParam(value = "name",required = false)String name){
        log.info("rest query user by name {}",name);
        return Constant.SUCCESS;
    }

    /**
     * @RequestBody
     * 接收json格式数据{"id":1,"userName":"jimmy"}自动转换成bean对象
     * @param user
     * @return
     */
    @PostMapping("/user")
    public String addUser1(@RequestBody User user){
        log.info("rest add user {}",user);
        return Constant.SUCCESS;
    }

    @PutMapping("/user")
    public String modifyUser(){
        log.info("rest modify user");
        return Constant.SUCCESS;
    }

    @DeleteMapping("/user")
    public String delUser(){
        log.info("rest delete user");
        return Constant.SUCCESS;
    }
}
