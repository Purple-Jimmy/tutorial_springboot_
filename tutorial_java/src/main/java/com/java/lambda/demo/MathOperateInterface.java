package com.java.lambda.demo;

/**
 * @Author: jimmy
 * @Date: 2018/8/9
 */
@FunctionalInterface
public interface MathOperateInterface {
    /**
     * 操作
     * @param a
     * @param b
     * @return
     */
    int operate(int a,int b);
}
