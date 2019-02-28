package com.tutorial.aop.proxy.staticproxy;

/**
 * @author jimmy
 * @date 2019-02-2823:36
 */
public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeRepository employeeRepository;

    @Override
    public void save(Employee employee){
        employeeRepository.save(employee);
    }

    @Override
    public void update(Employee employee){
        employeeRepository.update(employee);
        throw new RuntimeException("抛错");
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
}
