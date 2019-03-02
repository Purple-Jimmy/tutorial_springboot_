package com.tutorial.spring.service;

import com.tutorial.spring.entity.Employee;

/**
 * @author jimmy
 * @date 2019-02-2823:54
 */
public interface IEmployeeService {

    void save(Employee employee);

    void update(Employee employee);
}
