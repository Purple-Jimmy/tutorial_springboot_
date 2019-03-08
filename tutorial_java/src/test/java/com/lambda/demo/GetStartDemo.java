package com.lambda.demo;

import com.java.domain.Car;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: jimmy
 * @Date: 2018/8/7
 */

public class GetStartDemo {


    @Test
    public void getStart_12(){
       List<Car> carList = new ArrayList<>();
       Car c1 = new Car(1L,"m1",100L);
       Car c2 = new Car(2L,"m2",100L);
       Car c3 = new Car(3L,"m3",100L);
       carList.add(c1);
       carList.add(c2);
       carList.add(c3);

       List<Long> nameList = new ArrayList<>();
       List<Car> list = carList.stream().map(p->{
           p.setBrand("3");
           nameList.add(p.getId());
           return p;
       }).collect(Collectors.toList());
        System.out.println(nameList);
        System.out.println(list);

        carList.stream().forEach(s->s.setPrice(10L));
        System.out.println(carList);
    }


    @Test
    public void getStart_1(){
        Car car = new Car();
    }


    @Test
    public void getStart_2(){
        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1L,"lexus",100L));
        carList.add(new Car(2L,"bmw",50L));
        carList.add(new Car(3L,"benz",200L));
        carList.add(new Car(4L,"audi",40L));

        //按照price从低到高排序
        // Collections.sort(carList,(c1,c2)-> (int) (c1.getPrice()-c2.getPrice()));
        //System.out.println(carList);
        //按照price从高到低排序
        Collections.sort(carList,(c1, c2)-> (int) (c2.getPrice()-c1.getPrice()));
        System.out.println(carList);
    }

    //List 遍历
    @Test
    public void getStart_3(){
        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1L,"lexus",100L));
        carList.add(new Car(2L,"bmw",50L));
        carList.add(new Car(3L,"benz",200L));
        carList.forEach(car -> System.out.println(car));
        //先过滤再遍历
        carList.stream().filter(car -> car.getBrand().contains("e")).forEach(car -> System.out.println(car));
    }

    //map
    @Test
    public void getStart_4(){
        Map<String,Object> map = new HashMap<>();
        map.put("A","10");
        map.put("B","40");
        map.put("C","20");
        //遍历
        map.forEach((key,value)-> System.out.println(key+"="+value));
    }

    //groupBy 分组
    @Test
    public void getStart_5(){
        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1L,"lexus",100L));
        carList.add(new Car(2L,"bmw",50L));
        carList.add(new Car(3L,"benz",200L));
        carList.add(new Car(4L,"benz",500L));
        Map<String,List<Car>> map = carList.stream().collect(Collectors.groupingBy(car->car.getBrand()));
       // {benz=[Car(id=3, brand=benz, price=200), Car(id=4, brand=benz, price=500)],
        // lexus=[Car(id=1, brand=lexus, price=100)],
        // bmw=[Car(id=2, brand=bmw, price=50)]}
    }

    //map 过滤
    @Test
    public void getStart_filterMap(){
       Map<Integer,String> map = new HashMap<>();
       map.put(1,"AX");
       map.put(2,"XX");
       map.put(3,"A");

       String str = map.entrySet().stream().filter(m->"A".equals(m.getValue()))
               .map(m->m.getValue()).collect(Collectors.joining());
       System.out.println(str);

       Map<Integer,String> result = map.entrySet().stream().filter(m->"A".equals(m.getValue()))
               .collect(Collectors.toMap(p->p.getKey(),p->p.getValue()));
        System.out.println(result);
    }

    //Optional 防止NullPointException
    @Test
    public void getStart_operation(){
        Optional<String> optional = Optional.of("hello");
        System.out.println(optional.get());//hello
        System.out.println(optional.isPresent());//true
        System.out.println(optional.orElse("world"));//hello
        optional.ifPresent(s -> System.out.println(s.charAt(0)));//h
    }

    @Test
    public void getStart_all(){
        List<Car> carList = new ArrayList<>();
        carList.add(new Car(1L,"lexus",100L));
        carList.add(new Car(2L,"bmw",50L));
        carList.add(new Car(3L,"benz",200L));
        carList.add(new Car(4L,"benz",500L));
        //anyMatch
        Boolean anyMatch = carList.stream().anyMatch(car -> car.getBrand().equals("lexus1"));
        System.out.println("anyMatch-"+anyMatch);//false

        //allMatch
        Boolean allMatch = carList.stream().allMatch(car -> car.getPrice()>10);
        System.out.println("allMatch-"+allMatch);//true

        //找出最高价位
        Optional<Car> highCar = carList.stream().max(Comparator.comparingLong(Car::getPrice));
        System.out.println("highCar-"+highCar);

        //返回品牌列表
        List<String> brandList = carList.stream().map(car -> car.getBrand()).collect(Collectors.toList());
        System.out.println("brandList-"+brandList);

        //统计品牌是benz的数量
        Long benzCount = carList.stream().filter(car -> car.getBrand().equals("benz")).count();
        System.out.println("benzCount-"+benzCount);

        //list 转换map
        Map<Long,Car> map1 = carList.stream().collect(Collectors.toMap((key->key.getId()),(value->value)));
        System.out.println(map1);
        //
        Map<Long,Car> map2 = carList.stream().collect(Collectors.toMap(Car::getId,value->value));
        System.out.println(map2);

        //list 转换set
        Set<String> brandSet = carList.stream().map(car -> car.getBrand()).distinct().collect(Collectors.toSet());
        System.out.println("brandSet-"+brandSet);

        //查找品牌是benz的car
        Optional<Car> benzCars = carList.stream().filter(car -> car.getBrand().equals("benz")).findAny();
        System.out.println("benzCars-"+benzCars);

        //按照价格升序
        List<Car> carPriceAsc= carList.stream().sorted(Comparator.comparingLong(Car::getPrice)).collect(Collectors.toList());
        System.out.println("carPriceAsc-"+carPriceAsc);

        //按照价格降序
        List<Car> carPriceDesc = carList.stream().sorted((c1,c2)->Long.compare(c2.getPrice(),c1.getPrice())).collect(Collectors.toList());
        System.out.println("carPriceDesc-"+carPriceDesc);

        //获取价格最高的前2量car
        List<Car> top2Car = carList.stream().sorted((c1,c2)->Long.compare(c2.getPrice(),c1.getPrice())).limit(2).collect(Collectors.toList());
        System.out.println("top2Car-"+top2Car);

        //获取平均价格
        OptionalDouble averagePrice = carList.stream().mapToDouble(car->car.getPrice()).average();
        System.out.println("averagePrice-"+averagePrice);

        //获取benz的平均价格
        OptionalDouble benzAvgPrice = carList.stream().filter(car -> car.getBrand().equals("benz")).mapToDouble(car->car.getPrice()).average();
        System.out.println("benzAvgPrice-"+benzAvgPrice);

        //打印benz品牌的car
        carList.forEach(car -> {if(car.getBrand().equals("benz")){
            System.out.println(car.getId());
        }});

        //找出price>100的car
        List<Car> over100Car = carList.stream().filter(car -> car.getPrice()>100L).collect(Collectors.toList());
        System.out.println("over100Car-"+over100Car);
    }
}
