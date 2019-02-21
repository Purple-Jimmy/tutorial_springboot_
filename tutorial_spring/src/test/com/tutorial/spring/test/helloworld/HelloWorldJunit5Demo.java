package com.tutorial.spring.test.helloworld;

import com.tutorial.spring.helloworld.HelloWorld;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author jimmy
 * @date 2019-02-1923:20
 */
@SpringJUnitConfig()
@ContextConfiguration("classpath:applicationContext.xml")
public class HelloWorldJunit5Demo {
    @Autowired
    HelloWorld helloWorld;

    @Test
    public void test(){
        helloWorld.sayHello();
    }
}
