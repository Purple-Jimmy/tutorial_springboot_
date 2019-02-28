package com.tutorial.aop.proxy.staticproxy;

/**
 * @author jimmy
 * @date 2019-02-2823:33
 */
public interface EmployeeRepository {

    void save(Employee employee);

    void update(Employee employee);
}
