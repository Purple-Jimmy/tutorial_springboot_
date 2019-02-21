package com.tutorial.spring.test.helloworld;

import com.tutorial.spring.helloworld.HelloWorld;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author jimmy
 * @date 2019-02-1922:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
/**
 * 上下配置对象(寻找配置文件)
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class HelloWorldJunit4Demo {
    @Autowired
    HelloWorld helloWorld;

    @Test
    public void test(){
        helloWorld.sayHello();
    }
}
