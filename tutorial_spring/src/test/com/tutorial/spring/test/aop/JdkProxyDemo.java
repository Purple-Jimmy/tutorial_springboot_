package com.tutorial.spring.test.aop;

import com.tutorial.spring.entity.Employee;
import com.tutorial.spring.service.IEmployeeService;
import com.tutorial.spring.tx.TransactionManagerAdvice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author jimmy
 * @date 2019-03-0209:30
 */
@SpringJUnitConfig
@ContextConfiguration("classpath:applicationContext-proxy-jdk.xml")
public class JdkProxyDemo {
    @Autowired
    TransactionManagerAdvice transactionAdvice;

    /**
     * 代理对象 com.sun.proxy.$Proxy35
     */
    @Test
    public void saveTest(){
        //获取一个代理对象
        IEmployeeService proxyService = transactionAdvice.getProxyObject();
        System.out.println(proxyService.getClass());
        proxyService.save(new Employee());
    }

    @Test
    public void updateTest(){
        //获取一个代理对象
        IEmployeeService proxyService = transactionAdvice.getProxyObject();
        System.out.println(proxyService.getClass());
        proxyService.update(new Employee());
    }

}
