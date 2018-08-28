package com.java8.demo;

import com.java.domain.Car;
import org.junit.Test;

import java.util.Optional;

/**
 * https://www.cnblogs.com/yw0219/p/7354938.html
 * @Author: jimmy
 * @Date: 2018/8/28
 */
public class OptionalDemo {
    /**
     * 如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象
     */
    @Test
    public void test_1(){
        Car car1 = null;
        Car car2 = new Car();
        car2.setId(1L);
        car2.setBrand("Benz");
        //为非null的值创建一个Optional。
        //of方法通过工厂方法创建Optional类。需要注意的是，创建对象时传入的参数不能为null。
        //如果传入参数为null，则抛出NullPointerException
        Optional<Car> optionalCar = Optional.of(new Car());

        //ofNullable
        //为指定的值创建一个Optional，如果指定的值为null，则返回一个空的Optional。
        Optional<Car> carNull = Optional.ofNullable(car1);
        Optional<Car> carBenz = Optional.ofNullable(car2);
        System.out.println(carNull);//Optional.empty
        System.out.println(carBenz);//Optional[Car(id=1, brand=Benz, price=null)]

        //isPresent判断是否为空
        System.out.println(carNull.isPresent());//false
        System.out.println(carBenz.isPresent());//true

        //get 如果Optional有值则将其返回，否则抛出NoSuchElementException。
       // System.out.println(carNull.get());//java.util.NoSuchElementException: No value present
        System.out.println(carBenz.get());//Car(id=1, brand=Benz, price=null)

        //orElse 如果有值则将其返回，否则返回指定的其它值。
        System.out.println(carNull.map(car -> car.getBrand()).orElse("unknown"));//unknown
        System.out.println(carBenz.map(car -> car.getBrand()).orElse("unknown"));//Benz

        // orElseGet与orElse方法类似，区别在于得到的默认值。orElse方法将传入的字符串作为默认值，
        // orElseGet方法可以接受Supplier接口的实现用来生成默认值

        //抛出异常
       // System.out.println(carNull.map(car -> car.getBrand()).orElseThrow(()->new IllegalArgumentException("The value of param comp isn't available.")));//unknown

        // ifPresent 如果Optional实例有值则为其调用consumer，否则不做处理

        //检验参数的合法性
      /*  Optional.ofNullable(name).filter(User::isNameValid)
                .orElseThrow(()->new IllegalArgumentException("Invalid username."));*/

      //map
        //如果有值，则对其执行调用mapping函数得到返回值。如果返回值不为null，
        // 则创建包含mapping返回值的Optional作为map方法返回值，否则返回空Optional。

        //flatMap
        //如果有值，为其执行mapping函数返回Optional类型返回值，否则返回空Optional。

        //filter
        //如果有值并且满足断言条件返回包含该值的Optional，否则返回空Optional。
    }
}
