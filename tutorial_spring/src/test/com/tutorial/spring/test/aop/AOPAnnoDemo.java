package com.tutorial.spring.test.aop;

import com.tutorial.spring.entity.Employee;
import com.tutorial.spring.service.IEmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * aop注解方式
 * @author jimmy
 * @date 2019-03-0421:05
 */
@SpringJUnitConfig
@ContextConfiguration("classpath:applicationContext-aop-anno.xml")
public class AOPAnnoDemo {
    @Autowired
    IEmployeeService employeeService;

    @Test
    public void saveTest(){
        System.out.println(employeeService.getClass());
        employeeService.save(new Employee());
    }

    @Test
    public void updateTest(){
        employeeService.update(new Employee());
    }
}
