package com.tutorial.spring.test.ioc;


import com.tutorial.spring.anno.ioc.LifeCycleAnno;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author jimmy
 * @date 2019-02-2521:22
 */
@SpringJUnitConfig
@ContextConfiguration("classpath:applicationContext-ioc.xml")
public class IoCDemo {
    @Autowired
    private LifeCycleAnno bean1;

    @Autowired
    private LifeCycleAnno bean2;


    @Test
    public void test(){
        System.out.println(bean1);
        System.out.println(bean2);
    }
}
