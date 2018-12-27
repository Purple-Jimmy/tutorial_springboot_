package com.java.thread.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: jimmy
 * @Date: 2018/12/27
 * 在日常Java Web开发中难免遇到需要把一个参数层层的传递到最内层,然后中间层根本不需要使用这个参数,
 * 或者是仅仅在特定的工具类中使用,这样我们完全没有必要在每一个方法里面都传递这样一个通用的参数.
 * 如果有一个办法能够在任何一个类里面想用的时候直接拿来使用就太好了.
 * Java的Web项目大部分都是基于Tomcat,每次访问都是一个新的线程,这样让我们联想到了ThreadLocal,
 * 每一个线程都独享一个ThreadLocal,在接收请求的时候set特定内容,在需要的时候get这个值.
 */
public class ThreadLocalDemo {
    public static void main(String[] args) {
        //用法
        String dateString = sdfThreadLocal.get().format(new Date());
        System.out.println(dateString);
    }

    /**
     * initialValue()是一个protected方法
     * 一般是用来在使用时进行重写的,如果在没有set的时候就调用get,会调用initialValue方法初始化内容
     */
    private static ThreadLocal<SimpleDateFormat> sdfThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
}
