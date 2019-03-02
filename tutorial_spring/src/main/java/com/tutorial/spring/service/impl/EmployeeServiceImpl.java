package com.tutorial.spring.service.impl;

import com.tutorial.spring.entity.Employee;
import com.tutorial.spring.repository.IEmployeeRepository;
import com.tutorial.spring.service.IEmployeeService;

/**
 * @author jimmy
 * @date 2019-02-2823:36
 */
public class EmployeeServiceImpl implements IEmployeeService {
    private IEmployeeRepository employeeRepository;

    @Override
    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    @Override
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
