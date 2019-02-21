package com.tutorial.spring.test.helloworld;

import com.tutorial.spring.helloworld.SpringBeanLifeCycle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * bean生命周期测试
 * @author jimmy
 * @date 2019-02-2121:41
 */
@SpringJUnitConfig()
@ContextConfiguration("classpath:applicationContext.xml")
public class SpringBeanLifeCycleDemo {
    @Autowired
    SpringBeanLifeCycle springBeanLifeCycle;


    /**
     * scope="singleton"
     * 创建对象
     * 开启资源
     * 工作。。。
     * 关闭资源
     *
     *
     * scope="prototype" bean的作用域为prototype时容器只负责创建和初始化,并不会被spring容器管理,交给用户自己调用
     * 创建对象
     * 开启资源
     * 工作。。。
     *
     * 多例情况下spring不知道何时销毁bean,所以此时需要手动关闭资源
     */
    @Test
    public void lifeCycleTest(){
        springBeanLifeCycle.doWork();
    }
}
