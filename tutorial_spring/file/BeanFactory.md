### 什么是BeanFactory
BeanFactory是spring最古老的接口,表示spring IoC容器--生成bean对象的工厂,负责创建,配置和管理bean.  
被spring IoC容器管理的对象称为bean.


### spring IoC容器如何知道哪些是它管理的对象
spring IoC容器通过读取配置文件中的配置元数据,通过元数据对应用中的各个对象进行实例化以及装配

## 元数据配置的3种方式
1. XML-based
2. Annotation-based
3. Java-based

### spring IoC管理bean的原理
1. 通过Resource对象加载配置文件
2. 解析配置文件,得到指定名字的bean
3. 解析bean元素,id作为bean的名字,class用于反射得到bean实例.此时bean类必须存在一个无参构造函数(和访问权限无关)
4. 调用getBean方法时从容器中返回对象实例
就是把代码从java文件转移到xml中