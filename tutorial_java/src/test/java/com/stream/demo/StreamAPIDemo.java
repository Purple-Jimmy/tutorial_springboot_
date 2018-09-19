package com.stream.demo;

import com.java.domain.Car;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * @author jimmy
 * @date 2018/8/1220:22
 */
public class StreamAPIDemo {

    List<Car> carList = Arrays.asList(new Car(1L,"lexus",100L),
                                      new Car(2L,"benz",200L),
                                      new Car(3L,"audi",50L),
                                      new Car(4L,"lexus",300L)
            );
    /**
     * 筛选与切片
     * filter   接收lambda,从流中排除某些元素
     * limit    截断流
     * skip(n)  跳过元素,返回一个扔掉了前n个元素的流,若流中元素不足n个,则返回空流
     * distinct 筛选,通过流所生成元素的hashCode()和equals()去除重复元素
     */

    @Test
    public void streamAPITest_1(){

    }

    /**
     * 映射
     * map      接收lambda,将元素转换成其他形式或提取信息.接收一个函数作为参数,该函数会作用到每个元素上,并将其映射成一个新的元素
     * flatMap  接收一个函数作为参数,将流中的每一个值都换成另一个流,然后把所有流连接成一个流
     */
    @Test
    public void StreamMapTest(){
        carList.stream().map(Car::getPrice).forEach(System.out::println);
    }


    /***
     * 排序
     * sorted   自然排序(comparable)
     * sorted(Comparator comparator)  定制排序
     */


    /**
     * 查找与匹配
     * allMatch  是否匹配所有元素
     * anyMatch  是否至少匹配一个元素
     * noneMatch 是否没有匹配所有元素
     * findFirst 返回第一个元素
     * findAny   返回当前流中的任意元素
     * count     返回流中元素的总个数
     * max       返回流中最大值
     * min       返回流中最小值
     */


    /**
     * 归约
     * reduce 可以将流中元素反复结合起来,得到一个值
     */
    @Test
    public void ReduceTest(){

    }


    /**
     * 收集
     * collect 将流转换为其他形式,接收一个Collector接口的实现,用于给Stream中元素做汇总的方法
     */


    /**
     * 多级分组
     * 先按品牌分,再按价格分
     */
    @Test
    public void groupTest(){
        Map<String, Map<Long,List<Car>>> map = carList.stream().collect(Collectors.groupingBy(Car::getBrand,Collectors.groupingBy(Car::getPrice)));
        System.out.println(map);
    }


    /**
     * 并行流
     */
    @Test
    public void streamTest(){
        LongStream.rangeClosed(1,100000L).sequential().reduce(0,Long::sum);//顺序流
        LongStream.rangeClosed(1,100000L).parallel().reduce(0,Long::sum);//并行流
    }

    /**
     * 并行流
     * 如果forEachOrdered()中间有其他如filter()的中介操作,会试着平行化处理,然后最终forEachOrdered()会以原数据顺序处理,
     * 因此,使用forEachOrdered()这类的有序处理,可能会(或完全失去)失去平行化的一些优势,实际上中介操作亦有可能如此,例如sorted()方法
     *
     * lambda的执行并不是瞬间完成的,所有使用parallel streams的程序都有可能成为阻塞程序的源头,并且在执行过程中程序中的其他部分将无法访问这些workers,
     * 这意味着任何依赖parallel streams的程序在什么别的东西占用着common ForkJoinPool时将会变得不可预知并且暗藏危机
     *
     */
    @Test
    public void parallelStreamTest(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //展示顺序不一定会是1、2、3、4、5、6、7、8、9，而可能是任意的顺序
        numbers.parallelStream().forEach(System.out::print);
        System.out.println();
        //希望最后顺序是按照原来Stream的数据顺序
        numbers.parallelStream().forEachOrdered(System.out::print);

    }
}
