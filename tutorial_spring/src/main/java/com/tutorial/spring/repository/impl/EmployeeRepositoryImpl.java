package com.tutorial.spring.repository.impl;

import com.tutorial.spring.entity.Employee;
import com.tutorial.spring.repository.IEmployeeRepository;

/**
 * @author jimmy
 * @date 2019-02-2823:35
 */
public class EmployeeRepositoryImpl implements IEmployeeRepository {
    @Override
    public void save(Employee employee) {
        System.out.println("保存");
    }

    @Override
    public void update(Employee employee) {
        System.out.println("更新");;
    }
}
