package com.tutorial.spring.test.aop.proxy.statics;

import com.tutorial.spring.entity.Employee;
import com.tutorial.spring.service.IEmployeeService;
import com.tutorial.spring.tx.TransactionManager;

/**
 * 静态代理类
 * @author jimmy
 * @date 2019-02-2823:52
 */
public class EmployeeServiceProxy implements IEmployeeService {

    /**
     * 真实对象(委托对象)
     */
    private IEmployeeService target;

    /**
     * 模拟事务管理器
     */
    private TransactionManager transactionManager;

    @Override
    public void save(Employee employee) {
        transactionManager.begin();
        try {
            target.save(employee);
            transactionManager.commit();
        } catch (Exception e){
            transactionManager.rollback();
        }
    }

    @Override
    public void update(Employee employee) {
        transactionManager.begin();
        try {
            target.update(employee);
            transactionManager.commit();
        } catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback();
        }
    }

    public IEmployeeService getTarget() {
        return target;
    }

    public void setTarget(IEmployeeService target) {
        this.target = target;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
}
