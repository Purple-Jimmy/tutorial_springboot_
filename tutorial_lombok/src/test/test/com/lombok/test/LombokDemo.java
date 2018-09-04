package com.lombok.test;

import com.lombok.domain.Car;
import com.lombok.domain.Lexus;
import org.junit.Test;

/**
 * @Author: jimmy
 * @Date: 2018/9/4
 */
public class LombokDemo {

    /**
     * @Builder(toBuilder = true)
     */
    @Test
    public void builderTest(){
        Car car = Car.builder().id(1L).brand("Benz").build();
        System.out.println(car);//Car(id=1, brand=Benz)
        //修改属性值
        car = car.toBuilder().id(2L).brand("Lexus").build();
        System.out.println(car);//Car(id=2, brand=Lexus)
    }

    @Test
    public void extendBuilderTest(){
        Lexus lexus = (Lexus) Lexus.builder().id(1L).brand("lexus").build();
        System.out.println(lexus);
    }
    /**
     * @Accessors(chain = true)
     * fluent boolean值，默认为false。此字段主要为控制生成的getter和setter方法前面是否带get/set
     * chain boolean值，默认false。如果设置为true，setter返回的是此对象，方便链式调用方法
     * prefix 设置前缀 例如：@Accessors(prefix = "abc") private String abcAge 当生成get/set方法时，会把此前缀去掉
     */
    @Test
    public void accessorsTest(){
        Lexus lexus = (Lexus) new Lexus().setId(1L).setBrand("Lexus");
        System.out.println(lexus.getId());
    }

    /**
     * @ToString
     *
     */
    @Test
    public void toStringTest(){
        Lexus lexus = (Lexus)new Lexus().setId(1L).setBrand("Lexus");//Lexus(price=null)
        //@ToString(callSuper = true) 调用父类的toString Lexus(super=Car(id=1, brand=Lexus), price=null)
        //@ToString(callSuper = true,exclude = "price") 不能设置剔除父类的字段
        System.out.println(lexus);
    }

    // @NoArgsConstructor生成一个无参构造方法。当类中有final字段没有被初始化时，编译器会报错，
    // 此时可用@NoArgsConstructor(force = true)，然后就会为没有初始化的final字段设置默认值 0 / false / null。
    // 对于具有约束的字段（例如@NonNull字段），不会生成检查或分配，因此请注意，正确初始化这些字段之前，这些约束无效


    // @RequiredArgsConstructor会生成构造方法（可能带参数也可能不带参数），如果带参数，这参数只能是以final修饰的未经初始化的字段，
    // 或者是以@NonNull注解的未经初始化的字段

    // @RequiredArgsConstructor(staticName = "of")会生成一个of()的静态方法，并把构造方法设置为私有的

    // @AllArgsConstructor 生成一个全参数的构造方法



}
