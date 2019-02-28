package com.tutorial.spring.aop.test;

import com.tutorial.aop.proxy.staticproxy.Employee;
import com.tutorial.aop.proxy.staticproxy.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * @author jimmy
 * @date 2019-02-2823:48
 */
@SpringJUnitConfig
@ContextConfiguration("classpath:applicationContext-proxy.xml")
public class ProxyStaticDemo {
    @Autowired
    EmployeeService employeeServiceProxy;

    @Test
    public void saveTest(){
        System.out.println(employeeServiceProxy.getClass());
        employeeServiceProxy.save(new Employee());
    }

    @Test
    public void updateTest(){
        employeeServiceProxy.update(new Employee());
    }
}
