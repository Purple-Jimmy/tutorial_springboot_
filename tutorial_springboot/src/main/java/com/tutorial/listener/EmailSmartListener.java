package com.tutorial.listener;


import com.tutorial.controller.UserRegisterController;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by Jimmy. 2018/5/30  14:09
 */
@Component
public class EmailSmartListener implements SmartApplicationListener {

    /**
     * aClass与指定监听类型相同时,才会调用该监听内的onApplicationEvent方法
     * @param aClass 接收到的监听事件类型
     * @return
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> aClass) {
        //只有EmailRegisterEvent监听类型才会执行下面逻辑
        return aClass == EmailRegisterEvent.class;
    }

    @Override
    public boolean supportsSourceType(Class<?> aClass) {
        //只有在UserRegisterController内发布的EmailRegisterEvent事件时才会执行下面逻辑
        return aClass == UserRegisterController.class;
    }

    /**
     * supportsEventType & supportsSourceType 两个方法返回true时调用该方法执行业务逻辑
     * @param applicationEvent 具体监听实例,这里是EmailRegisterEvent
     */
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        //转换事件类型
        EmailRegisterEvent emailRegisterEvent = (EmailRegisterEvent) applicationEvent;
        //获取注册用户对象信息
       // UserBean user = userRegisterEvent.getUser();
        //.../完成注册业务逻辑
        System.out.println("email smart ...");
    }

    //值越小 优先执行
    @Override
    public int getOrder() {
        return 0;
    }
}
