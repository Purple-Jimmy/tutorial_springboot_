package com.tutorial.controller;

import com.tutorial.domain.User;
import com.tutorial.listener.EmailRegisterEvent;
import com.tutorial.listener.UserRegisterEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jimmy. 2018/5/30  11:36
 */
@RestController
public class UserRegisterController {

    @Autowired
    private ApplicationContext applicationContext;

    /**
     * 发布注册事件
     * @return
     */
    @RequestMapping("/userRegister")
    public String userRegister(){
        User user = new User();
        user.setUserName("jimmy");
        //发布用户注册事件
        applicationContext.publishEvent(new UserRegisterEvent(this,user));
        //发布邮件发送事件
        applicationContext.publishEvent(new EmailRegisterEvent(this));
        return "userRegister";
    }
}
