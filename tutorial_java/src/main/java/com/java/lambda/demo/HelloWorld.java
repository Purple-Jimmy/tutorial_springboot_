package com.java.lambda.demo;

/**
 * 在自定义的函数式接口的地方使用lambda表达式
 * @Author: jimmy
 * @Date: 2018/8/7
 */
public class HelloWorld {

    /**
     * 执行接口中的saySomeThing方法
     * @param action
     * @param str
     */
    public static void excuteSay(CustomActionInterface action,String str){
            action.sayHello(str);
    }

    public static String excuteSaySuccess(CustomActionInterface action,String str){
        action.sayHello(str);
        return "success";
    }
    public static void main(String[] args) {
       /* excuteSay(new CustomActionInterface(){
            @Override
            public void sayHello(String str) {
                System.out.println(str);
            }
        },"Hello World");*/

        excuteSay(str -> System.out.println(str),"hello");

        String result = excuteSaySuccess(str -> {},"haha");
        System.out.println(result);
    }
}
