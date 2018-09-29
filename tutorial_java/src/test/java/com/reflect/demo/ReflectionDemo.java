package com.reflect.demo;

import com.java.domain.RefPerson;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jimmy
 * @date 2018/9/2320:35
 */
public class ReflectionDemo {

    @Test
    public void reflectionTest() throws IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        Class<RefPerson> clazz = RefPerson.class;
        //1.创建clazz对应的运行时类RefPerson类的对象
        RefPerson refPerson = clazz.newInstance();
        System.out.println(refPerson);

        //2.获取fields clazz.getFields() 属性私有时获取不到
        Field[] fieldArray = clazz.getDeclaredFields();
        for(Field field:fieldArray){
            System.out.println(field.getName());
        }
        //属性是私有的
        Field idField = clazz.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(refPerson,1);
        System.out.println(refPerson);

        //3.获取methods
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method:methods){
            //System.out.println(method.getName());
        }
        //4.方法反射调用 method.invoke(反射对象,参数);
        Method showAgeMethod = clazz.getMethod("showAge");
        showAgeMethod.invoke(refPerson);
    }
}
