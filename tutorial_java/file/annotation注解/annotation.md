# annotation 注解
源代码的元数据.本质是一个附属品,依赖其他元素(包,类,方法,参数)而存在,由外部程序解析产生作用.
在Java中,注解的作用就是告诉开发人员,被注解的内容是用来做什么的,换句话说,注解就是Java代码的标签

## 注解的原理
```
注解本质是一个继承了Annotation的特殊接口,其具体实现类是Java运行时生成的动态代理类.而我们通过反射获取注解时,
返回的是Java运行时生成的动态代理对象$Proxy1,通过代理对象调用自定义注解(接口)的方法,会最终调用AnnotationInvocationHandler的invoke方法.
该方法会从memberValues这个Map中索引出对应的值,而memberValues的来源是Java常量池
```

## 注解分类
* 按运行机制分
1. source源码注解:只存在于源码中,编译成class文件后移除
2. class编译时注解:源码和编译成class文件时都会存在,编译时存在,运行时移除@Override @Deprecated @SuppressWarnings
3. runtime运行时注解:运行时也还存在 @Autowired

* 按来源分
1. 内置注解
2. 第三方注解
3. 自定义注解


## 元注解
1. @Retention:定义了该注解的生命周期,作用就是说明这个注解的存活时间
```
1. RetentionPolicy.SOURCE:注解只在源码阶段保留,在编译器完整编译之后,它将被移除,如@Override, @SuppressWarnings
2. RetentionPolicy.CLASS:注解只被保留到编译进行的时候,它并不会被加载到JVM中
3. RetentionPolicy.RUNTIME:注解可以保留到程序运行的时候,它会被加载到JVM中,所以在程序运行时可以获取到它们
```

2. @Target:表示该注解用于什么地方,可以理解为当一个注解被@Target注解时,这个注解就被限定了运用的场景
```
1. ElementType.CONSTRUCTOR:对构造方法进行注解
2. ElementType.ANNOTATION_TYPE:对注解进行注解
3. ElementType.FIELD:对属性、成员变量、成员对象(包括enum实例)进行注解
4. ElementType.LOCAL_VARIABLE:对局部变量进行注解
5. ElementType.METHOD:对方法进行注解；
6. ElementType.PACKAGE:对包进行注解；
7. ElementType.PARAMETER:对描述参数进行注解
8. ElementType.TYPE:对类、接口、枚举进行注解
```

3. @Documented:是一个标记注解,表示是否将注解信息添加在Java文档即Javadoc中

4. @Inherited:定义了一个注释与子类的关系.如果一个超类带有@Inherited注解,对于该超类,它的子类如果没有被任何注解应用的话,那么这个子类就继承了超类的注解
```

```

5. @Repeatable:Java8中加入的,是指可重复的意思.通常使用@Repeatable的时候指注解的值可以同时取多个
```

```


## 注解的属性
```
注解的属性也叫做成员变量,注解只有成员变量没有方法.注解的成员变量在注解的定义中以无形参的方法形式来声明,
其方法名定义了该成员变量的名字,其返回值定义了该成员变量的类型.注解可以有默认值,需要用default关键字指定.
果注解内只有一个名为 value 的属性时，应用该属性时可以将值直接写到括号内，不用写 value = "..."
```


## 自定义注解




## 注解解析
1. JDK反射获取注解信息
2. spring容器解析