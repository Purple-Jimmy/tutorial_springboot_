package com.tutorial.spring.service.impl;

import com.tutorial.spring.entity.Employee;
import com.tutorial.spring.repository.IEmployeeRepository;
import com.tutorial.spring.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jimmy
 * @date 2019-02-2823:36
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
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
