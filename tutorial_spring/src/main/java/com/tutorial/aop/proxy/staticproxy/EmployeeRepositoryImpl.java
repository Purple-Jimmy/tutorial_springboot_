package com.tutorial.aop.proxy.staticproxy;

/**
 * @author jimmy
 * @date 2019-02-2823:35
 */
public class EmployeeRepositoryImpl implements EmployeeRepository {
    @Override
    public void save(Employee employee) {
        System.out.println("保存");
    }

    @Override
    public void update(Employee employee) {
        System.out.println("更新");;
    }
}
