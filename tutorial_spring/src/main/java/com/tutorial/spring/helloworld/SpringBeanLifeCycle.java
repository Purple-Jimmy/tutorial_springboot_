package com.tutorial.spring.helloworld;

/**
 * bean的生命周期
 * @author jimmy
 * @date 2019-02-2121:32
 */
public class SpringBeanLifeCycle {

    public SpringBeanLifeCycle() {
        System.out.println("创建对象");
    }


    public void open(){
        System.out.println("开启资源");
    }


    public void close(){
        System.out.println("关闭资源");
    }

    public void doWork(){
        System.out.println("工作。。。");
    }
}
