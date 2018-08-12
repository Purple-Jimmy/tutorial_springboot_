### lambda表达式

#### Functional Interface. 函数式接口
```
定义的接口里面必须有且只有一个抽象方法,这样的接口就称为函数式接口.   
一般用@FunctionalInterface标注出来(也可以不标)
```

#### 使用场景
```
在可以使用lambda表达式的地方,方法声明时必须包含一个函数式的接口.
(JAVA8的接口可以有多个default方法)
任何函数式接口都可以使用lambda表达式替换,例如:ActionListener、Comparator、Runnable
lambda表达式只能出现在目标类型为函数式接口的上下文中.
```
如果接口包含一个以上的Abstract Method,那么使用lambda表达式则会报错

#### 语法     参数列表 -> 所需执行的功能(lambda体)

   
   
   
