package com.java.lambda.demo;

/**
 * 自定义函数式接口
 * @Author: jimmy
 * @Date: 2018/8/7
 */
public interface CustomActionInterface {

    void sayHello(String str);

    /**
     * Java8引入的新特性 接口中可以定义一个default方法的实现 (不是abstract)
     */
    default void say() {
        System.out.println("default say");
    }
}
