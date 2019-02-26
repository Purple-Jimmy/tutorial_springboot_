package com.java.collection.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: jimmy
 * @Date: 2019/2/22
 */
public class ListDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for(int i=1;i<=10;i++){
            list.add(i+"");
        }
        list.add("6");
        System.out.println(list);

        for(String i:list){
            if(i.equalsIgnoreCase("6")){
                list.remove(i);
                break;
            }
        }
        System.out.println(list);
       /* System.out.println(list);
        System.out.println(list.subList(0,3));
        System.out.println(list.subList(list.size()-3,list.size()));*/
    }
}
