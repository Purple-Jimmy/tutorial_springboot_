package com.java.base;

import com.java.domain.Car;
import com.java.domain.City;
import org.junit.Test;

/**
 * java.lang.Object是所有类的父类,没有定义属性,只有方法
 * @author jimmy
 * @date 2019-02-0110:46
 */
public class ObjectDemo {

    /**
     * Car重写了toString方法,因为知道属性是什么
     * Car(id=1, brand=null, price=null)
     * city未重写toString方法,此时Object类并不知道具体对象的属性,无法用文本描述,但又得区分不同对象
     * com.java.domain.City@64b8f8f4 其中64b8f8f4是对象的哈希值,通常是对象的内存地址值
     *
     */
    @Test
    public void toStringTest(){
        Car car = new Car();
        car.setId(1L);
        System.out.println(car.toString());

        City city = new City();
        city.setId(1L);
        System.out.println(city.toString());
    }
}
