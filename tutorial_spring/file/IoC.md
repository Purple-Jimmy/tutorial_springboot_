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



## bean实例化过程


## IoC注解配置