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

    /**
     * 装箱拆箱
     */
    @Test
    public void packingAndUnPackingTest(){
        //基本类型
       // byte byte_1 =




        //引用类型
        Byte by1 = new Byte("123");

        Integer a = 10;
      //  Byte byte_1 = wa;

    }

    @Test
    public void charTest(){
        char char_en  = 'c';//任意单个字符,加单引号
        char char_zh  = '中';//任意单个中文字,加单引号
        char char_num = 111;///整数 0~65535 十进制、八进制、十六进制均可,输出字符编码表中对应的字符

        //char类型是可以运算的因为char在ASCII等字符编码表中有对应的数值
        //在java中,对char类型字符运行时,直接当做ASCII表对应的整数来对待
        char m1 ='a'+'b'; //Ã
        System.out.println("m1="+m1);
        int m2 ='a'+'b'; //195没有超出int范围,直接输出195
        System.out.println("m2="+m2);
        char m3 = 197; //Å 输出字符编码表中对应的字符
        System.out.println("m3="+m3);
        char m4 ='a'+1; //提升为int,计算结果98对应的字符是b
        System.out.println("m4="+m4);
    }
    /**
     * char m='中'+'国';　　——42282。
     *
     * char m='中'+'国'+'国'+'国';　　——报错。int转char有损失。因为结果已经超出char类型的范围。
     *
     * int m='中'+'国'+'国'+'国';　　——86820
     *
     * char m='中'+1;　　——丮。//1是int，结果提升为int，输出对应的字符。
     *
     * char m='中'+"国";　　——报错。String无法转换为char。
     *
     * System.out.println('中'+"国");　　——中国。//没有变量附值的过程。String与任何字符用“+”相连，转换为String。
     * 用单引号''标识，只能放单个字符。
     * char+char，char+int——类型均提升为int，附值char变量后，输出字符编码表中对应的字符
     */

    @Test
    public void longTest(){
        Long l1 = 10000L;
        Long l2 = 10000L;
        System.out.println(l1 == l2);//false
        System.out.println(l1.equals(l2));//true

        Integer i1 = 10000;
        Integer i2 = 10000;

        System.out.println(i1 == i2);//false
        System.out.println(i1.equals(i2));//true

        Integer i3 = 127;
        Integer i4 = 127;

        System.out.println(i3 == i4);//true
        System.out.println(i3.equals(i4));//true

    }
}
