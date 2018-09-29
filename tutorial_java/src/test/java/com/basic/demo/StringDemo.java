package com.basic.demo;

import org.junit.Test;

/**
 * @author jimmy
 * @date 2018/8/921:21
 */
public class StringDemo {

    @Test
    public void stringTest(){

        String str = new String("Hello");
        System.out.println(str);//hello
        str = new String("world");
        System.out.println(str);//world
    }

    @Test
    public void stringTest_1(){
        String str = "I love 中国! 蒙虓暁!";
        byte[] bytes = str.getBytes();
        for(int i=0;i<bytes.length;i++){
            System.out.print((char)bytes[i]);//I love ￤ﾸﾭ￥ﾛﾽ! ￨ﾒﾙ￨ﾙﾓ￦ﾚﾁ!
        }

        char[] chars = str.toCharArray();
    }
}
