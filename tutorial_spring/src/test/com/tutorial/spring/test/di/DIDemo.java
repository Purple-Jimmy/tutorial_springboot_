package com.tutorial.spring.test.di;

import com.tutorial.spring.di.Empolyee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author jimmy
 * @date 2019-02-2420:34
 */
@SpringJUnitConfig()
@ContextConfiguration("classpath:applicationContext.xml")
public class DIDemo {
    @Autowired
    Empolyee empolyee1;


    @Test
    public void test(){
        System.out.println(empolyee1);
    }
}
