package com.collection.demo;

import com.java.domain.Car;
import org.junit.Test;

import java.util.*;

/**
 * @author jimmy
 * @date 2018/9/1509:52
 */
public class SetDemo {

    /**
     * Set特点:元素不重复且无序,但是无序不代表随机
     * 无序是指底层元素存储的无序性,由hash值决定存储位置
     * 自定义对象一定要重写equals()和hashCode()方法
     * set中元素存储使用了hash算法
     */
    @Test
    public void hashSetTest(){
        Set set = new HashSet<>();
        set.add(123);
        set.add(456);
        set.add("AA");
        set.add("BB");
        set.add(null);//可以添加null
        System.out.println(set);//[AA, BB, null, 456, 123] 运行多次打印结果顺序都一样
    }


    /**
     * LinkedHashSet:使用链表维护了元素添加进的顺序,遍历的时候按照添加的顺序遍历,但底层存储依然是无序的
     */
    @Test
    public void linkedHashSetTest(){
        Set set = new LinkedHashSet<>();
        set.add(123);
        set.add(456);
        set.add("AA");
        set.add("BB");
        set.add(null);//可以添加null
        System.out.println(set);//[123, 456, AA, BB, null]
    }


    /**
     * TreeSet
     * 1. 只能添加同一个类的对象
     * 2. String,包装类等默认按照从小到大顺序遍历
     * 3. 自定义对象需要实现Comparable接口
     * 4. TreeSet中添加元素时,首先根据compareTo()进行比较,若相等(返回0),虽然比较的是两个对象的某个属性值,但是TreeSet也认为是相同的两个对象,后面的元素加入不进去
     */
    @Test
    public void treeSetTest(){
        Set set = new TreeSet<>();
        set.add("VV");
        set.add("AA");
        set.add("GG");
        System.out.println(set);//[AA, GG, VV]
    }

    /**
     * 定制排序
     */
    @Test
    public void customSort(){
       /* Comparator comparator = new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                if((o1 instanceof Car) && (o2 instanceof Car)){
                    Car car1 = (Car)o1;
                    Car car2 = (Car)o1;
                    //按照价格排序
                    return car1.getPrice().compareTo(car2.getPrice());
                }
                return 0;
            }
        };*/

        //1.创建一个实现了comparator接口的类对象
        Comparator comparator = (o1, o2) -> {
            int i = 0;
            if((o1 instanceof Car) && (o2 instanceof Car)){
                Car car1 = (Car)o1;
                Car car2 = (Car)o2;
                //按照价格排序
                i =  car1.getPrice().compareTo(car2.getPrice());
              //  i =  car1.getBrand().compareTo(car2.getBrand());
            }
            return i;
        };
        //2. 将comparator作为形参传递给TreeSet构造器中
        TreeSet treeSet = new TreeSet(comparator);
        //3. 添加元素
        treeSet.add(new Car(1L,"Lexus",100L));
        treeSet.add(new Car(4L,"Benz",60L));
        treeSet.add(new Car(2L,"Audi",700L));
        treeSet.add(new Car(3L,"BMW",30L));

        Iterator iterator = treeSet.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
