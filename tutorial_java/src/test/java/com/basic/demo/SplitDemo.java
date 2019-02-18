package com.basic.demo;

import org.junit.Test;

/**
 * @Author: jimmy
 * @Date: 2019/2/18
 */
public class SplitDemo {
    @Test
    public void test(){

        String s = "A+B+C D F+G";
        s = s.replaceAll("\\+"," ");
        System.out.println(s);
        String[] array = s.split(" ");
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }
}
