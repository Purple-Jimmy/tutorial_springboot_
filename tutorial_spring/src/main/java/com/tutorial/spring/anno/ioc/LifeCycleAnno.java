package com.tutorial.spring.anno.ioc;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author jimmy
 * @date 2019-02-2521:19
 */
@Component
//@Scope("prototype")
public class LifeCycleAnno {

    public LifeCycleAnno() {
        System.out.println("创建对象");
    }

    /**
     * 构建对象之后
     */
    @PostConstruct
    public void open(){
        System.out.println("开启资源");
    }

    /**
     * 销毁之前
     */
    @PreDestroy
    public void close(){
        System.out.println("关闭资源");
    }

    public void doWork(){
        System.out.println("工作。。。");
    }
}
