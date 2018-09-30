package com.tutorial.listener;

import com.tutorial.domain.User;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by Jimmy. 2018/5/30  10:28
 * 注册事件 注解方式
 */
@Component
public class RegisterListener {

    @EventListener
    public void register(UserRegisterEvent userRegisterEvent)
    {
        //获取注册用户对象
        User user = userRegisterEvent.getUser();

        //../省略逻辑

        //输出注册用户信息
        System.out.println("@EventListener注册信息,用户名："+user.getUserName()+",密码："+user.getPassword());
    }
}
