package com.reflect.demo;

import com.java.domain.Car;
import com.java.domain.Lexus;
import org.junit.Test;

import java.lang.reflect.*;

/**
 * @author jimmy
 * @date 2019-03-1222:29
 */
public class ReflectBasicDemo {

    /**
     * 类的加载
     */
    @Test
    public void classLoadTest() {
        /*
        public static Class<?> forName(String className)
        public static Class<?> forName(String name, boolean initialize, ClassLoader loader)
        initialize􏱟􏰬􏱫􏱾􏱖􏱲􏱁􏰻􏲂􏲇􏱹􏰈􏱺􏱄􏰎􏰙􏱌表示加载后是否执行类的初始化代码(如static语句块)
        第一个方法相当于 Class.forName(className, true, currentLoader)
        currentLoader表示加载当前类的ClassLoader
        */
    }


    /**
     * Class有一个newInstance()方法可以创建对象,会调用默认的无参public构造方法
     * 如果没有此构造方法会抛出异常
     */
    @Test
    public void classTest() throws IllegalAccessException, InstantiationException {
        Car car = Car.class.newInstance();
        System.out.println(car);
        //获取所有的public构造方法
        Constructor<?>[] constructors = Car.class.getConstructors();
        System.out.println(constructors);
        //获取所有的构造方法,包括非public的
        Constructor<?>[] allConstructor= Car.class.getDeclaredConstructors();
        System.out.println(allConstructor);
    }

    /**
     * Class类信息
     */
    @Test
    public void classTInfoest() {
        /*
        public native boolean isArray();//是否是数组
        public native boolean isPrimitive();//是否是基本类型
        public native boolean isInterface();//是否是接口
        public boolean isEnum();//是否是枚举
        public boolean isAnnotation();//是否是注解
        public boolean isAnonymousClass();//是否是匿名内部类
        public boolean isMemberClass();//是否是成员类,成员类定义在方法外,不是匿名类
        public boolean isLocalClass();//是否是本地类,本地类定义在方法内,不是匿名类
        */
    }


    /**
     * Constructor类表示构造方法
     * constructor.newInstance()可以创建对象
     */
    @Test
    public void constructorTest() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<StringBuilder> constructor = StringBuilder.class.getConstructor(new Class[]{int.class});
        System.out.println(constructor.newInstance(100));
    }

    /**
     * 获取名称信息
     * com.java.domain.Car
     * Car
     * com.java.domain.Car
     * package com.java.domain
     *
     */
    @Test
    public void nameInfoTest(){
        Car car = new Car();
        Class clazz = car.getClass();
        //返回Java􏱰􏱋􏰙􏰰􏰈􏲅􏱾􏰈􏰿􏱺内部真正使用的名字
        System.out.println(clazz.getName());
        //返回name不带包信息
        System.out.println(clazz.getSimpleName());
        System.out.println(clazz.getCanonicalName());
        //返回包信息
        System.out.println(clazz.getPackage());
    }

    /**
     * 获取字段信息
     */
    @Test
    public void fieldInfoTest1() throws NoSuchFieldException {
        Car car = new Car();
        Class clazz = car.getClass();
        //返回所有的public字段,包括父类的
        Field[] allPub = clazz.getFields();
        System.out.println(allPub);
        //返回本类的所有字段,包括私有的,但不包含父类的
        Field[] self = clazz.getDeclaredFields();
        System.out.println(self);
        //返回本类或父类中指定名称的public字段,找不到抛出异常NoSuchFieldException
        System.out.println(clazz.getField("id"));
        //返回本类中声明的指定名称的字段,找不到抛出异常NoSuchFieldException
        System.out.println(clazz.getDeclaredField("brand"));
    }

    /**
     * brand
     * false
     * 设置访问权限true:true
     * lexus
     * bmw
     * 2
     * private
     * isPublic: false
     * isStatic: false
     * isFinal: false
     * isVolatile: false
     * class java.lang.String
     */
    @Test
    public void fieldInfoTest2() throws NoSuchFieldException, IllegalAccessException {
        Car car = new Car();
        car.setPrice(100L);
        car.setBrand("lexus");
        Field field = Car.class.getDeclaredField("brand");
        //获取字段的名称
        System.out.println(field.getName());
        //判断当前程序是否有该字段的访问权限
        System.out.println(field.isAccessible());
        //true:忽略java的访问检查机制,允许读写非public的字段
        field.setAccessible(true);
        System.out.println("设置访问权限true:"+field.isAccessible());
        //获取指定对象obj中该字段的值
        System.out.println(field.get(car));
        //将指定对象obj中该字段的值设置为value
        field.set(car,"bmw");
        System.out.println(field.get(car));
        //返回字段修饰符
        int mod = field.getModifiers();
        System.out.println(mod);
        System.out.println(Modifier.toString(mod));
        System.out.println("isPublic: " + Modifier.isPublic(mod));
        System.out.println("isStatic: " + Modifier.isStatic(mod));
        System.out.println("isFinal: " + Modifier.isFinal(mod));
        System.out.println("isVolatile: " + Modifier.isVolatile(mod));
        //返回字段类型
        System.out.println(field.getType());
    }

    /**
     * [Ljava.lang.reflect.Method;@3a4afd8d
     * [Ljava.lang.reflect.Method;@1996cd68
     * public java.lang.String com.java.domain.Car.getBrand()
     * public void com.java.domain.Car.setBrand(java.lang.String)
     * public java.lang.Long com.java.domain.Car.getPrice()
     */
    @Test
    public void methodInfoTest1() throws ClassNotFoundException, NoSuchMethodException {
        Class clazz = Class.forName("com.java.domain.Car");
        //获取所有的public方法,包括父类的
        System.out.println(clazz.getMethods());
        //获取本类的所有方法,包括私有的,但不包括父类的
        System.out.println(clazz.getDeclaredMethods());
        //返回本类或父类中指定名称和参数类型的public方法
        System.out.println(clazz.getMethod("getBrand"));
        System.out.println(clazz.getMethod("setBrand",String.class));
        //返回本类中声明的指定名称和参数类型的方法
        System.out.println(clazz.getMethod("getPrice"));
    }

    /**
     * getBrand
     * Lexus
     */
    @Test
    public void methodInfoTest2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Car car = new Car();
        car.setBrand("Lexus");
        Method method = car.getClass().getMethod("getBrand");
        //获取方法的名称
        System.out.println(method.getName());
        //true:忽略java的访问检查机制,允许读写非public的方法
        method.setAccessible(true);
        //在指定对象obj上调用Method代表的方法,参数列表为args
        System.out.println(method.invoke(car));
    }


    /**
     * isInstance和cast描述的是对象和类之间的关系
     * isAssignableFrom描述类型之间的关系
     * 类型检查和强制转换
     */
    @Test
    public void typeCheck() throws ClassNotFoundException {
        Lexus lexus = new Lexus();
        //静态类型检查 instanceof 后面的类型是确定的
        System.out.println(lexus instanceof Car);
        //动态类型检查
        Class clazz = Class.forName("com.java.domain.Car");
        System.out.println(clazz.isInstance(lexus));
        //静态强制转换
        Car car = (Car)lexus;
        System.out.println(car);
        //动态强制转换
        Lexus l2 = new Lexus();
        System.out.println(clazz.cast(l2));
        //描述类与类之间的关系 参数类型cla能否赋值给当前Class类型的变量
        Object.class.isAssignableFrom(String.class);
    }

    /**
     * 反射获取数组的元素类型
     */
    @Test
    public void arrayMemberType(){
        String[] arr = new String[]{};
        System.out.println(arr.getClass().getComponentType());
    }

}
