# reflect 反射


## Class类
每个已加载的类在内存中都有一份类信息,每个对象都有指向它所属类信息的引用.Java中类信息对应的类就是java.lang.Class.
所有类的父类Object有一个getClass()方法可以获取类对象.

### 获取类对象
1. Object的getClass()方法  public final native Class<?> getClass()
2. 类名.class获取  Class<Date> cls = Date.class;
3. 接口也适用      Class<Comparable> cls = Comparable.class;
4. 基本类型没有getClass方法,但有相应的class对象 Class<Integer> intCls = int.class;
5. Void也有 Class<Void> voidCls = void.class;
6. 数组
```
String[] strArr = new String[10];
int[][] twoDimArr = new int[3][2];
int[] oneDimArr = new int[10];
Class<? extends String[]> strArrCls = strArr.getClass();
Class<? extends int[][]> twoDimArrCls = twoDimArr.getClass();
Class<? extends int[]> oneDimArrCls = oneDimArr.getClass();
```
7. 枚举类型
8. 通过Class的静态方法   Class<?> cls = Class.forName("java.util.HashMap");



