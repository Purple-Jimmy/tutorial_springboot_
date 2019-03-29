package com.java.base;

import org.junit.Test;

/**
 * @author jimmy
 * @date 2019-03-2921:29
 */

public class VarDemo {
    /**
     * 自动类型提升
     * a向上提升变为int类型
     */
    @Test
    public void test1(){
        byte a = 3;
        int  b = 3;
        b = a + b;
        System.out.println(b);
        //结果:6
    }

    /**
     * 编译不通过
     * byte a = 3;在编译的时候检查3在byte范围之内,把int类型的3转换成byte
     * a = a +1;是一个计算,并不确定计算完的结果是否在byte范围之内
     */
    @Test
    public void test2(){
        byte a = 3;
       // a = a + 1;
    }

    /**
     * 强制类型转换
     */
    @Test
    public void test3(){
        byte a = 3;
        a = (byte)(a + 1);
        System.out.println(a);
        System.out.println((byte)(a + 1000));
        //结果:4      -20
    }
}
