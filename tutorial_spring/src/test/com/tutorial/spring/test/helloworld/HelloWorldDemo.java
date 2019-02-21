package com.tutorial.spring.test.helloworld;

import com.tutorial.spring.helloworld.HelloWorld;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author jimmy
 * @date 2019-02-1920:30
 */
public class HelloWorldDemo {

    /**
     * 传统方式:调用者手动创建对象和创建对象依赖的对象,并组装依赖关系
     */
    @Test
    public void test(){
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setId(1);
        helloWorld.setName("Jimmy");

        helloWorld.sayHello();
    }

    /**
     * 使用spring方式 BeanFactory
     */
    @Test
    public void TestWithSpring(){
        HelloWorld helloWorld = null;
        //从classpath寻找配置文件创建资源对象
        Resource resource = new ClassPathResource("applicationContext.xml");
        //根据资源对象创建spring的IoC容器对象
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        //从IoC容器中获取指定名称的对象 3种方式
        //方式1:根据name获取bean helloWorld即为xml中配置的id属性名字
        helloWorld = (HelloWorld) beanFactory.getBean("helloWorld");
        //方式2:按照type获取bean,要求在spring中只配置一个这种类型的实例
        helloWorld = beanFactory.getBean(HelloWorld.class);
        //方式3:按照name和type获取
        helloWorld = beanFactory.getBean("helloWorld",HelloWorld.class);

        helloWorld.sayHello();
    }

    /**
     * 模拟spring ioc容器
     */
    @Test
    public void springIoCMock() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException {
        String className = "com.tutorial.spring.helloworld.HelloWorld";
        HelloWorld helloWorld = null;
        //----------------------------
        //使用反射创建对象
        Class clazz = Class.forName(className);
        Constructor constructor = clazz.getConstructor();
        constructor.setAccessible(true);
        Object object = clazz.newInstance();
        //使用内省机制设置属性值
        BeanInfo beanInfo = Introspector.getBeanInfo(clazz,Object.class);
        //获取helloWorld中所有的属性名称
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
        for(PropertyDescriptor pd:pds){
            if("id".equals(pd.getName())){
                //调用id的setter方法
                pd.getWriteMethod().invoke(object,3);
            }
            if("name".equals(pd.getName())){
                pd.getWriteMethod().invoke(object,"lucy");
            }
        }
        //
        helloWorld = (HelloWorld) object;
        //----------------------------
        helloWorld.sayHello();
    }


    /**
     * 使用spring方式 ApplicationContext
     * ApplicationContext是BeanFactory的子接口
     */
    @Test
    public void testApplicationContext(){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloWorld helloWorld = ctx.getBean("helloWorld",HelloWorld.class);
        System.out.println(helloWorld);
        //手动销毁bean
        //registerShutdownHook 把spring线程作为JVM的子线程,JVM虚拟机在结束之前要把spring先结束掉
        ((ClassPathXmlApplicationContext) ctx).registerShutdownHook();
    }
}
