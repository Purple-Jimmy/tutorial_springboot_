package com.tutorial.spring.repository;

import com.tutorial.spring.entity.Employee;

/**
 * @author jimmy
 * @date 2019-02-2823:33
 */
public interface IEmployeeRepository {

    void save(Employee employee);

    void update(Employee employee);
}
