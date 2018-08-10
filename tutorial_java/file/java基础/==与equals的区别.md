### ==与equals的区别

#### ==
1. 引用数据类型比较的是对象的内存地址(指针地址)
2. 具体的阿拉伯数字比较,值相等则相等
3. 常用来比较8种基本数据类型(byte、short、long、double、char、int、float、boolean),比较它们的值


#### equals
1. 引用数据类型比较的也是堆内存地址,适用于所有对象,因为对象都继承自java.lang.Object类.如果没有对该方法进行覆盖的话,调用的仍然是Object类中的方法,
而Object中的equals方法返回的却是==的判断


#### String Date Integer 对equals方法进行了重写,比较的是对象的内容是否相等
1. String str = "abc";是一种特殊的形式,和new有本质的区别,它是java中唯一不需要new就可以产生对象的途径
```
String str = "abc"; 声明后JVM首先在常量池中查找有没有值为abc的对象,若有则把它赋给当前引用,若没有则在常量池新建一个abc
String str = new String("abc");和其他对象一样,每调用一次就新建一个对象
```
String 源码
```
/**
     * Compares this string to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * String} object that represents the same sequence of characters as this
     * object.
     *
     * @param  anObject
     *         The object to compare this {@code String} against
     *
     * @return  {@code true} if the given object represents a {@code String}
     *          equivalent to this string, {@code false} otherwise
     *
     * @see  #compareTo(String)
     * @see  #equalsIgnoreCase(String)
     */
   
 public boolean equals(Object anObject) {//anObject是传进来的要进行比较的对象
	       //如果当前对象和传进来要进行比较的对象anObject是同一个对象（即地址相同eg同一辆汽车（只有一辆））则返回true
		 if (this == anObject) {
	            return true;
	        }
	        if (anObject instanceof String) {//如果传进来的需要进行比较的对象anObject是String类的实例，则把anObject转换成String类型
	            String anotherString = (String) anObject;
	            //value是一个private final char value[];
	            //String类的构造函数已经给value[]初始化了
	            //value.length代表原先要比较对象的字符个数
	            int n = value.length;
	            //如果两者的字符个数不相等，意味着两者不可能相等，所以返回false；否则，依次遍历比较两者的每一个字符，若每一个字符都相等则相等，否则不想等
	            if (n == anotherString.value.length) {
	                char v1[] = value;
	                char v2[] = anotherString.value;
	                int i = 0;
	                while (n-- != 0) {
	                    if (v1[i] != v2[i])
	                            return false;
	                    i++;
	                }
	                return true;
	            }
	        }
	        return false;
	    }

```


#### 在重写equals方法的同时,必须重写hashCode方法