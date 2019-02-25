package com.tutorial.spring.anno.ioc;

import org.springframework.stereotype.Component;

/**
 * @author jimmy
 * @date 2019-02-2521:19
 */
@Component
public class LifeCycleAnno {

    public LifeCycleAnno() {
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
