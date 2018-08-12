package com.lambda.demo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 四大内置核心函数式接口
 * @author jimmy
 * @date 2018/8/1216:31
 */
public class FourBuiltinInterfaceDemo {

    //需求:产生指定个数的整数,放入集合中
    @Test
    public void supplierTest(){
        List<Integer> list = getNumList(10,() -> (int)(Math.random()*100));
        System.out.println(list);
    }

    public List<Integer> getNumList(int num, Supplier<Integer> supplier){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<num;i++){
            Integer m = supplier.get();
            list.add(m);
        }
        return list;
    }


    //需求:字符串处理
    @Test
    public void functionTest(){

        String result = strHandler1("hello world",(s) -> s.substring(0,5));
        System.out.println(result);

        int length = strHandler2("hello world",(s) -> s.length());
        System.out.println(length);
    }

    public String strHandler1(String str, Function<String,String> function){
        return function.apply(str);
    }

    public int strHandler2(String str, Function<String,Integer> function){
        return function.apply(str);
    }


    //需求:将满足条件的字符串放入集合中
    @Test
    public void predicateTest(){
        List<String> list = Arrays.asList("hello","world","AA","B");
        System.out.println(filterStr(list,(s) -> s.length()>3));
    }

    public List<String> filterStr(List<String> list, Predicate<String> predicate){
        List<String> result = new ArrayList<>();
        for(String str:list){
            if(predicate.test(str)){
                result.add(str);
            }
        }
        return result;
    }
}
