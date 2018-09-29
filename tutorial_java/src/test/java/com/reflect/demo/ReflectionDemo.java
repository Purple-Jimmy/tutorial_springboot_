package com.reflect.demo;

import com.java.domain.RefPerson;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * @author jimmy
 * @date 2018/9/2320:35
 */
public class ReflectionDemo {

    @Test
    public void reflectionTest() throws IllegalAccessException, InstantiationException, NoSuchFieldException {
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
    }
}
