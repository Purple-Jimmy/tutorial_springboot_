package com.tutorial.spring.tx;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 事务的增强操作
 * @author jimmy
 * @date 2019-03-0209:12
 */
public class TransactionManagerAdvice implements java.lang.reflect.InvocationHandler{

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
        return (T) Proxy.newProxyInstance(targetObj.getClass().getClassLoader(), targetObj.getClass().getInterfaces(), this);
    }


    /**
     * 如何为真实对象的方法做增强的具体操作
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object = null;
        try{
            transactionManager.begin();
            //调用真实对象的方法
            object = method.invoke(targetObj,args);
            transactionManager.commit();
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback();
        }
        return object;
    }

    public Object getTargetObj() {
        return targetObj;
    }

    public void setTargetObj(Object targetObj) {
        this.targetObj = targetObj;
    }

    public TransactionManager getTransactionManager() {
        return transactionManager;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /* class xx implements InvocationHandler{

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }*/
}
