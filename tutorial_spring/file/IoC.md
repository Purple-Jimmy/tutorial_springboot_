### IoC

## ApplicationContext 应用上下文
ApplicationContext是BeanFactory的子接口


## bean的创建时机
BeanFactory和ApplicationContext获取bean的区别
```
BeanFactory具有延迟初始化(lazy)的特点,即在创建spring容器的时候,不会立马去创建容器中管理的bean对象,
而是等到需要从容器中获取对象的时候才去创建对象.
ApplicationContext在创建spring容器的时候,就会把容器中管理的bean立马初始化---web建议使用
```


## bean的实例化方式
1. 构造器实例化(无参构造器),最标准

2. 静态工厂方法实例化

3. 实例工厂方法实例化

4. 实现FactoryBean接口实例化:



## bean作用域
在spring容器中是指其创建的bean对象相对于其它bean对象的请求可见范围


## bean初始化和销毁
```
scope="singleton"
创建对象
开启资源
工作。。。
关闭资源
     
   
scope="prototype" bean的作用域为prototype时容器只负责创建和初始化,并不会被spring容器管理,交给用户自己调用
创建对象
开启资源
工作。。。
    
多例情况下spring不知道何时销毁bean,所以此时需要手动关闭资源
```


## bean实例化过程---bean从创建到销毁的整个过程
```
BeanFactory:延迟初始化的特点
Application:
1.启动spring容器
2.创建bean对象----实则在调用bean对象的构造器
3.调用bean对象的初始化方法init-method
4.我们获取bean对象,开始调用bean对象中的某一个方法
5.调用bean对象的销毁方法---destroy-method
6.spring容器销毁
```


## IoC注解配置
1. @Component
2. @Repository
3. @Service
4. @Controller