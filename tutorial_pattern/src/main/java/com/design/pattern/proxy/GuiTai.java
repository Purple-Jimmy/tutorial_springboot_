package com.design.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理对象
 * 柜台 卖烟卖酒
 * @author jimmy
 * @date 2018/8/2620:05
 */
public class GuiTai implements InvocationHandler {
    private Object wine;

    public GuiTai(Object wine){
        this.wine = wine;
    }

    /**
     *
     * @param proxy   代理对象
     * @param method  代理对象调用的方法
     * @param args    调用的方法中的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("销售开始  柜台是： "+this.getClass().getSimpleName());
        method.invoke(wine, args);
        System.out.println("销售结束");
        return null;
    }
   /* 销售开始  柜台是： GuiTai
      茅台酒
      销售结束*/
}
