package com.tutorial.spring.test.aop;

import com.tutorial.spring.entity.Employee;
import com.tutorial.spring.service.IEmployeeService;
import com.tutorial.spring.tx.TransactionManagerCglib;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author jimmy
 * @date 2019-03-0209:30
 */
@SpringJUnitConfig
@ContextConfiguration("classpath:applicationContext-proxy-cglib.xml")
public class CglibProxyDemo {
    @Autowired
    TransactionManagerCglib transactionManagerCglib;

    /**
     * 代理对象 com.sun.proxy.$Proxy35
     */
    @Test
    public void saveTest(){
        //获取一个代理对象
        IEmployeeService proxyService = transactionManagerCglib.getProxyObject();
        System.out.println("--=="+proxyService.getClass());
        proxyService.save(new Employee());
    }

    @Test
    public void updateTest(){
        //获取一个代理对象
        IEmployeeService proxyService = transactionManagerCglib.getProxyObject();
        System.out.println(proxyService.getClass());
        proxyService.update(new Employee());
    }

}
