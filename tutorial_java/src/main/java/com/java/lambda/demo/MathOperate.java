package com.java.lambda.demo;

/**
 * @Author: jimmy
 * @Date: 2018/8/9
 */
public class MathOperate {

    public static void main(String[] args) {
        MathOperateInterface mathOperateInterface = (a,b) -> a+b;
        int result = operate(1,2,mathOperateInterface);
        System.out.println(result);
    }


    public static int operate(int a,int b,MathOperateInterface mathOperateInterface){
        return mathOperateInterface.operate(a,b);
    }
}
