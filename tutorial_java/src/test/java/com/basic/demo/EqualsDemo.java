package com.basic.demo;

import org.junit.Test;

/**
 * ==与equals的区别
 * @author jimmy
 * @date 2018/8/823:29
 */
public class EqualsDemo {
    @Test
    public void equalsTest(){
        //a为一个引用
        String a = new String("ab");
        //b为一个引用且内容和一样
        String b = new String("ab");

        System.out.println(a == b);//false
        System.out.println(a.equals(b));//true


        String s1 = "ab";//放在常量池
        String s2 = "ab";//在常量池查找 s1和s2指向同一个对象
        System.out.println(s1 == s2);//true
        System.out.println(s1.equals(s2));//true

        System.out.println(a == s1);//false
        System.out.println(a.equals(s1));//true



        int  i = 10;
        long j= 10;
        System.out.println(i == j);//true

        int  m = 100000;
        long n = 100000;
        System.out.println(m == n);//true


        //String Integer对equals方法进行了重写
    }
}
