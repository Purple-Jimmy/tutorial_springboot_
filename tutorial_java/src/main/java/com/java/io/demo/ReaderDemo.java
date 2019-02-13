package com.java.io.demo;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

/**
 * @Author: jimmy
 * @Date: 2019/2/13
 */
public class ReaderDemo {

    public static void main(String args[]) throws Exception{
        // 第1步、使用File类找到一个文件    声明File对象
        File f= new File("d:" + File.separator + "test.txt") ;
        // 第2步、通过子类实例化父类对象 准备好一个输入的对象
        Reader input = null ;
        // 通过对象多态性进行实例化
        input = new FileReader(f)  ;
        // 第3步、进行读操作 所有的内容都读到此数组之中
        // 如果读取的内容过长(超过1024),将会报错 java.lang.ArrayIndexOutOfBoundsException: 1024
        char c[] = new char[1024] ;
        // 接收每一个内容
        int temp = 0 ;
        // 读取内容
        int len = 0 ;
        while((temp=input.read())!=-1){
            // 如果不是-1就表示还有内容，可以继续读取
            c[len] = (char)temp ;
            len++ ;
        }
        // 第4步、关闭输出流
        input.close() ;
        // 把字符数组变为字符串输出
        System.out.println("内容为：" + new String(c,0,len)) ;
    }
}
