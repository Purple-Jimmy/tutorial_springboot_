package com.tutorial.spring.service.impl;

import com.tutorial.spring.entity.Employee;
import com.tutorial.spring.repository.IEmployeeRepository;

/**
 * cglib代理可以针对没有接口的对象
 * @author jimmy
 * @date 2019-03-0221:29
 */
public class EmployeeService {
    private IEmployeeRepository employeeRepository;


    public void save(Employee employee){
        employeeRepository.save(employee);
    }


    public void update(Employee employee){
        employeeRepository.update(employee);
        throw new RuntimeException("抛错");
    }

    public IEmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public void setEmployeeRepository(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
