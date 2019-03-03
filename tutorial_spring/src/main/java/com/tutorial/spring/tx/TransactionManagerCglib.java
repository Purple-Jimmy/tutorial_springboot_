package com.tutorial.spring.tx;

import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Method;

/**
 * 事务的增强操作 cglib代理
 * @author jimmy
 * @date 2019-03-0221:31
 */
public class TransactionManagerCglib implements org.springframework.cglib.proxy.InvocationHandler {
    private TransactionManager transactionManager;
    /**
     * 真实对象 对谁做增强
     */
    private Object targetObj;

    /**
     * 创建一个代理对象
     * ClassLoader loader      类加载器,一般是真实对象的类加载器
     * Class<?>[] interfaces   真实对象所实现的接口
     * InvocationHandler h     xx 如何做事务增强的对象
     * @return
     */
    public <T> T getProxyObject(){
        Enhancer enhancer = new Enhancer();
        //继承哪一个类去做增强
        enhancer.setSuperclass(targetObj.getClass());
        //设置增强的对象
        enhancer.setCallback(this);
        //创建代理对象
        return (T) enhancer.create();
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println(o.getClass());
        Object object = null;
        try{
            transactionManager.begin();
            //调用真实对象的方法
            object = method.invoke(targetObj,objects);
            transactionManager.commit();
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback();
        }
        return object;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public Object getTargetObj() {
        return targetObj;
    }

    public void setTargetObj(Object targetObj) {
        this.targetObj = targetObj;
    }
}
