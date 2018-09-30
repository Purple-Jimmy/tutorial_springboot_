package com.tutorial.listener;

import com.tutorial.domain.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * Created by Jimmy. 2018/5/30  10:38
 * 用户注册事件
 * 监听都是围绕着事件来挂起
 */
@Getter
public class UserRegisterEvent extends ApplicationEvent {
    /**
     * 注册用户对象
     */
    private User user;

    /**
     * 重写构造函数
     * @param source 发生事件的对象
     * @param user 注册用户对象 user参数是我们自定义的注册用户对象,该对象可以在监听内被获取
     */
    public UserRegisterEvent(Object source,User user) {
        super(source);
        this.user = user;
    }
}
